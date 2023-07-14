import React, { useEffect, useState } from "react";
import "../../pages/Reward/Reward.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import moment from "moment";

const Rewarding = () => {
  const [rewardingRewards, setRewardingRewards] = useState([]);
  const [visibleRewards, setVisibleRewards] = useState(4);
  const [showLoadMoreButton, setShowLoadMoreButton] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetchRewardingRewards();
  }, []);

  const fetchRewardingRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8090/reward-detail/find/rewarding",
        {
          params: {
            startIndex: 0,
            endIndex: visibleRewards,
          },
        }
      );
      const fetchedRewards = response.data;
      setRewardingRewards(fetchedRewards);
      setShowLoadMoreButton(fetchedRewards.length >= visibleRewards);
      console.log(fetchedRewards);
    } catch (error) {
      console.error("Error fetching rewarding rewards:", error);
    }
  };

  const loadMoreRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8090/reward-detail/find/rewarding",
        {
          params: {
            startIndex: visibleRewards,
            endIndex: visibleRewards + 4,
          },
        }
      );
      const nextRewards = response.data;
      setRewardingRewards((prevRewards) => [...prevRewards, ...nextRewards]);
      setVisibleRewards((prevVisibleRewards) => prevVisibleRewards + 4);
      setShowLoadMoreButton(nextRewards.length >= 4);
    } catch (error) {
      console.error("Error loading more rewards:", error);
    }
  };

  const rewardCardRows = [];
  for (let i = 0; i < visibleRewards; i += 4) {
    const row = rewardingRewards.slice(i, i + 4);
    rewardCardRows.push(row);
  }

  return (
    <div className="rewarding">
      <p className="reward_title">진행중인 리워드</p>
      <div className="rewarding_proj">
        펀딩메이트에서 핫한 프로젝트를 만나보세요
      </div>
      <div className="reward_cards">
        {rewardCardRows.map((row, rowIndex) => (
          <div key={rowIndex} className="reward_card_row">
            {row.map((reward) => (
              <div
                className="reward_card"
                key={reward.id}
                onClick={() => {
                  navigate(`reward-detail/story/${reward.id}`);
                }}
              >
                <img
                  src={`http://localhost:8090/img/${reward.repFile.fileName}`}
                  className="reward_img"
                  alt={reward.projName}
                />
                <div className="com_name">{reward.manufacturer}</div>
                <div className="reward_name"> {reward.projName}</div>
                <div className="reward_detail">
                  <div className="price">12.345원 펀딩</div>
                  <div className="rate">
                    {/* {Math.floor((reward.currentAmount / reward.projTargetAmount) * 100)} */}
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
