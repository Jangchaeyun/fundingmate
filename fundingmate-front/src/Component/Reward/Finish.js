import React, { useEffect, useState } from "react";
import "../../pages/Reward/Reward.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Finish = () => {
  const [finishRewards, setFinishRewards] = useState([]);
  const [visibleRewards, setVisibleRewards] = useState(4);
  const [showLoadMoreButton, setShowLoadMoreButton] = useState(true);
  const [paymentAmountsData, setPaymentAmountsData] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    fetchFinishRewards();
  }, [visibleRewards]);

  const fetchFinishRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/reward/find/finishreward/more",
        {
          params: {
            startIndex: 0,
            endIndex: visibleRewards,
          },
        }
      );

      if (response.data && Array.isArray(response.data.data)) {
        // If the response data is available and contains the array of finish rewards
        const fetchedRewards = response.data.data;
        setFinishRewards(fetchedRewards);
        setShowLoadMoreButton(fetchedRewards.length >= visibleRewards);

        // Fetch payment amounts for all finish rewards
        const rewardIds = fetchedRewards.map((reward) => reward.id);
        const paymentResponse = await axios.get(
          "http://localhost:8080/payment/total-amount-same-rewards",
          {
            params: {
              rewardIds: rewardIds.join(","),
            },
          }
        );

        setPaymentAmountsData(paymentResponse.data);
      } else {
        // If the response data is not available or doesn't contain the array of finish rewards
        setFinishRewards([]);
        setShowLoadMoreButton(false);
        setPaymentAmountsData({});
      }
    } catch (error) {
      console.error("Error fetching finish rewards:", error);
      // Handle the error gracefully, show an error message, or take appropriate action
    }
  };

  useEffect(() => {
    if (finishRewards.length > 0) {
      const rewardIds = finishRewards.map((reward) => reward.id);
      fetchPaymentAmount(rewardIds);
    }
  }, [finishRewards]);

  const fetchPaymentAmount = async (rewardIds) => {
    try {
      const response = await axios.get(
        "http://localhost:8080/payment/total-amount-same-rewards",
        {
          params: {
            rewardIds: rewardIds.join(","),
          },
        }
      );
      setPaymentAmountsData(response.data);
    } catch (error) {
      console.error("Error fetching payment amounts:", error);
    }
  };

  const loadMoreRewards = async () => {
    const nextVisibleRewards = visibleRewards + 4;
    try {
      const response = await axios.get(
        "http://localhost:8080/reward/find/finishreward/more",
        {
          params: {
            startIndex: visibleRewards,
            endIndex: nextVisibleRewards,
          },
        }
      );
      const nextRewards = response.data.data; // Use response.data.data to get the array of finish rewards
      setFinishRewards((prevRewards) => [...prevRewards, ...nextRewards]);
      setVisibleRewards(nextVisibleRewards);
      setShowLoadMoreButton(nextRewards.length >= 4);
    } catch (error) {
      console.error("Error loading more rewards:", error);
    }
  };

  const handleRewardClick = (rewardId) => {
    window.location.href = `${window.location.origin}/rewarddetail/story/${rewardId}`;
  };

  return (
    <div className="rewarding">
      <p className="reward_title">종료된 리워드 프로젝트</p>
      <div className="rewarding_proj">마무리된 리워드 프로젝트입니다.</div>
      {Array.isArray(finishRewards) ? (
        <div className="reward_cards">
          {finishRewards.map((reward) => (
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
              <div className="com_name"> {reward.manufacturer}</div>
              <div className="reward_name">{reward.projName}</div>
              <div className="reward_detail">
                <div className="price">
                  {paymentAmountsData[reward.id]?.toLocaleString() || "0"}원
                  펀딩
                </div>
                <div className="rate">
                  {(
                    (paymentAmountsData[reward.id] / reward.projTargetAmount) *
                    100
                  ).toFixed(1)}
                  %
                </div>
                <div className="d_day">종료</div>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div>No finished rewards to display.</div>
      )}
      {showLoadMoreButton && (
        <button className="reward-more" onClick={loadMoreRewards}>
          종료된 프로젝트 더보기
        </button>
      )}
    </div>
  );
};

export default Finish;
