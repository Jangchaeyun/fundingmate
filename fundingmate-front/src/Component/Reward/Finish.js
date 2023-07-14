import React, { useEffect, useState } from "react";
import "../../pages/Reward/Reward.css";
import axios from "axios";

const Finish = () => {
  const [finishRewards, setFinishRewards] = useState([]);
  const [visibleRewards, setVisibleRewards] = useState(4);
  const [showLoadMoreButton, setShowLoadMoreButton] = useState(true);

  useEffect(() => {
    fetchFinishRewards();
  }, []);

  const fetchFinishRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8090/reward/find/finishreward/more",
        {
          params: {
            startIndex: 0,
            endIndex: visibleRewards,
          },
        }
      );
      const fetchedRewards = response.data;
      setFinishRewards(fetchedRewards);
      setShowLoadMoreButton(fetchedRewards.length >= visibleRewards);
    } catch (error) {
      console.error("Error fetching finish rewards:", error);
    }
  };

  const loadMoreRewards = async () => {
    const nextVisibleRewards = visibleRewards + 4;
    try {
      const response = await axios.get(
        "http://localhost:8090/reward/find/finishreward/more",
        {
          params: {
            startIndex: visibleRewards,
            endIndex: nextVisibleRewards,
          },
        }
      );
      const nextRewards = response.data;
      setFinishRewards((prevRewards) => [...prevRewards, ...nextRewards]);
      setVisibleRewards(nextVisibleRewards);
      setShowLoadMoreButton(nextRewards.length >= 4);
    } catch (error) {
      console.error("Error loading more rewards:", error);
    }
  };

  return (
    <div className="rewarding">
      <p className="reward_title">종료된 리워드 프로젝트</p>
      <div className="rewarding_proj">마무리된 리워드 프로젝트입니다.</div>
      <div className="reward_cards">
        {finishRewards.map((reward) => (
          <div className="reward_card" key={reward.id}>
            <img
              src={`http://localhost:8090/img/${reward.repFile.fileName}`}
              className="reward_img"
              alt={reward.projName}
            />
            <div className="com_name"> {reward.manufacturer}</div>
            <div className="reward_name">{reward.projName}</div>
            <div className="reward_detail">
              <div className="price">12.345원 펀딩</div>
              <div className="rate">107%</div>
              <div className="d_day">종료</div>
            </div>
          </div>
        ))}
      </div>
      {showLoadMoreButton && (
        <button className="reward-more" onClick={loadMoreRewards}>
          종료된 프로젝트 더보기
        </button>
      )}
    </div>
  );
};

export default Finish;
