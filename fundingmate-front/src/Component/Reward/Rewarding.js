import React, { useEffect, useState } from "react";
import "../../pages/Reward/Reward.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Rewarding = () => {
  const [rewardingRewards, setRewardingRewards] = useState([]);
  const [visibleRewards, setVisibleRewards] = useState(4);
  const [showLoadMoreButton, setShowLoadMoreButton] = useState(true);
  const [paymentAmountsData, setPaymentAmountsData] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    fetchInitialRewardingRewards();
  }, []);

  const fetchInitialRewardingRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/reward/find/rewarding/more",
        {
          params: {
            startIndex: 0,
            endIndex: visibleRewards
          }
        }
      );

      const rewardingRewardsData = response.data;
      setRewardingRewards(rewardingRewardsData);
      setShowLoadMoreButton(rewardingRewardsData.length >= 4);
    } catch (error) {
      console.error("Error fetching initial rewarding rewards:", error);
    }
  };

  const loadMoreRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/reward/find/rewarding/more",
        {
          params: {
            startIndex: visibleRewards,
            endIndex: visibleRewards + 4
          }
        }
      );

      const rewardingRewardsData = response.data;
      setRewardingRewards((prevRewards) => [
        ...prevRewards,
        ...rewardingRewardsData
      ]);
      setVisibleRewards((prevVisibleRewards) => prevVisibleRewards + 4);
      setShowLoadMoreButton(rewardingRewardsData.length >= 4);
    } catch (error) {
      console.error("Error fetching more rewarding rewards:", error);
    }
  };

  // useEffect(() => {
  //   fetchRewardingRewards();
  // }, [visibleRewards]);

  // const fetchRewardingRewards = async () => {
  //   try {
  //     const response = await axios.get(
  //       "http://localhost:8080/reward/find/rewarding/more",
  //       {
  //         params: {
  //           startIndex: 0,
  //           endIndex: visibleRewards
  //         }
  //       }
  //     );

  //     const rewardingRewardsData = response.data;
  //     setRewardingRewards((prevRewards) => [
  //       ...prevRewards,
  //       ...rewardingRewardsData
  //     ]);
  //     setShowLoadMoreButton(rewardingRewardsData.length >= 4);
  //   } catch (error) {
  //     console.error("Error fetching rewarding rewards:", error);
  //   }
  // };

  useEffect(() => {
    const rewardIds = rewardingRewards.map((reward) => reward.id);
    fetchPaymentAmount(rewardIds);
  }, [rewardingRewards]);

  const fetchPaymentAmount = async (rewardIds) => {
    try {
      const response = await axios.get(
        "http://localhost:8080/payment/total-amount-same-rewards",
        {
          params: {
            rewardIds: rewardIds.join(",")
          }
        }
      );

      setPaymentAmountsData(response.data);
    } catch (error) {
      console.error("Error fetching payment amounts:", error);
    }
  };

  const handleRewardClick = (rewardId) => {
    navigate(`/rewarddetail/story/${rewardId}`);
  };

  const numVisibleRewards = Math.min(visibleRewards, rewardingRewards.length);
  const visibleRewardCards = rewardingRewards.slice(0, numVisibleRewards);

  return (
    <div className="rewarding">
      <p className="reward_title">진행중인 리워드</p>
      <div className="rewarding_proj">
        펀딩메이트에서 핫한 프로젝트를 만나보세요
      </div>
      <div className="reward_cards">
        {visibleRewardCards.map((reward) => (
          <div
            className="reward_card"
            key={reward.id}
            onClick={() => handleRewardClick(reward.id)}
          >
            <img
              src={`http://localhost:8080/img/${reward.rewardRepImgSavedName}`}
              className="reward_img"
              alt={reward.projName}
            />
            <div className="com_name">{reward.manufacturer}</div>
            <div className="reward_name">{reward.projName}</div>
            <div className="reward_detail">
              <div className="price">
                {paymentAmountsData[reward.id]
                  ? `${paymentAmountsData[reward.id].toLocaleString()}원 펀딩`
                  : "0원 펀딩"}
              </div>
              <div className="rate">
                {(
                  (paymentAmountsData[reward.id] / reward.projTargetAmount) *
                  100
                ).toFixed(1)}
                %
              </div>
              <div className="d_day">
                D-
                {Math.ceil(
                  (new Date(reward.projDateEnd) - new Date()) /
                    (1000 * 60 * 60 * 24)
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
      {showLoadMoreButton && (
        <button className="reward-more" onClick={loadMoreRewards}>
          리워드 프로젝트 더보기
        </button>
      )}
    </div>
  );
};

export default Rewarding;
