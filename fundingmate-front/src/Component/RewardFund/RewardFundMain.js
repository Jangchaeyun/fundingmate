import React from "react";
import "../../pages/RewardFund/RewardFundMain.css";
import { useNavigate, useLocation } from "react-router-dom";
import Footer from "../../Component/Footer/Footer";
import Header from "../../Component/Header/Header";
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
      <Header />
      <div
        className="reward_make"
        style={{ marginBottom: "200px", marginTop: "35px" }}
      >
        <h1 className="makerewardfund">프로젝트 만들기</h1>
        <div className="reward_div">
          <div className="makereward_title" style={{ marginTop: "50px" }}>
            리워드 프로젝트
          </div>
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
          <div className="makefund_title" style={{ marginTop: "100px" }}>
            투자 프로젝트
          </div>
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
      <Footer />
    </div>
  );
};

export default RewardFundMain;
