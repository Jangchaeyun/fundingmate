import React, { useEffect, useState } from "react";
import "../../pages/Reward/Reward.css";
import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Preopen = () => {
  const [rewards, setRewards] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchPrewardRewards();
  }, []);

  const fetchPrewardRewards = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/reward/find/prereward"
      );
      setRewards(response.data);
    } catch (error) {
      console.error("Error while fetching prereward rewards", error);
    }
  };

  const handleRewardClick = (rewardId) => {
    navigate(`/rewarddetail/story/${rewardId}`);
  };

  return (
    <div className="reward">
      <p className="reward_title">사전 공개</p>
      <div className="rewarding_proj">
        곧 오픈할 프로젝트들을 가장 먼저 만나보세요.
      </div>
      {rewards.length >= 4 ? (
        <div className="reward_cards">
          <Swiper
            modules={[Navigation, Pagination, Scrollbar, A11y]}
            spaceBetween={6}
            slidesPerView={4}
            navigation
          >
            {rewards.map((reward) => (
              <SwiperSlide key={reward.id}>
                <div
                  className="reward_card"
                  key={reward.id}
                  onClick={() => handleRewardClick(reward.id)}
                >
                  <img
                    src={`http://localhost:8080/img/${reward.rewardRepImgSavedName}`}
                    className="reward_img"
                    alt={reward.rewardName}
                  />
                  <div className="com_name"> {reward.manufacturer}</div>
                  <div className="reward_name">{reward.projName}</div>
                  <div className="reward_detail">
                    <div className="d_day">
                      D-
                      {Math.ceil(
                        (new Date(reward.projDateEnd) - new Date()) /
                          (1000 * 60 * 60 * 24)
                      )}
                    </div>
                  </div>
                </div>
                <div className="bell_box">알 림 신 청</div>
              </SwiperSlide>
            ))}
          </Swiper>
        </div>
      ) : (
        <div className="reward_cards">
          {rewards.map((reward) => (
            <div key={reward.id} className="reward_card">
              <img
                src={`http://localhost:8080/img/${reward.rewardRepImgSavedName}`}
                className="reward_img"
                alt={reward.rewardName}
              />
              <div className="com_name"> {reward.manufacturer}</div>
              <div className="reward_name">{reward.projName}</div>
              <div className="reward_detail">
                <div className="open">{reward.projDateStart} 오픈</div>
              </div>
              <div className="bell_box">알 림 신 청</div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Preopen;
