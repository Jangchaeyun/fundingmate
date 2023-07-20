import React from "react";
import "../../pages/RewardFund/RewardFundMain.css";
import { useNavigate, useLocation } from "react-router-dom";
const RewardFundMain = () => {
  const navigate = useNavigate();

  const handleRewardButtonClick = () => {
    // 로컬 스토리지에서 토큰 가져오기
    const token = localStorage.getItem("token");

    // 토큰이 없으면 로그인 폼으로 설정
    if (!token) {
      navigate("/login");
    } else {
      navigate("/makeRewardBasicinfo");
    }
    // // Only navigate if the user is logged in
    // if (isLoggedIn) {
    //   navigate("/make-reward/basicinfo");
    // }
  };

  const handleFundButtonClick = () => {
    // 로컬 스토리지에서 토큰 가져오기
    const token = localStorage.getItem("token");

    // 토큰이 없으면 로그인 폼으로 설정
    if (!token) {
      navigate("/login");
    } else {
      navigate("/makeInvestBasicinfo");
    }
    // Only navigate if the user is logged in
    // if (isLoggedIn) {
    //   navigate("/make-invest/basicinfo");
    // }
  };

  return (
    <div>
      <div className="reward_make">
        <h1 className="makerewardfund">프로젝트 만들기</h1>
        <div className="reward_div">
          <div className="makereward_title">리워드 프로젝트</div>
          <img src="/assets/imgs/reward.png" className="makereward" />
          <button
            type="button"
            className="reward_button"
            onClick={handleRewardButtonClick}
          >
            만들기
          </button>
        </div>
        <div className="fund_div">
          <div className="makefund_title">투자 프로젝트</div>
          <img src="/assets/imgs/fund.png" className="makefund" />
          <button
            type="button"
            className="fund_button"
            onClick={handleFundButtonClick}
          >
            만들기
          </button>
        </div>
      </div>
    </div>
  );
};

export default RewardFundMain;
