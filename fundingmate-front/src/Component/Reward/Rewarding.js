import React from "react";
import "../../pages/Reward/Reward.css";

const Rewarding = () => {
  return (
    <div className="rewarding">
      <p className="reward_title">진행중인 리워드</p>
      <div class="rewarding_proj">
        펀딩메이트에서 핫한 프로젝트를 만나보세요
      </div>
      <div className="reward_cards">
        <div className="reward_card">
          <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
          <div className="company_name"> 스마트보이</div>
          <div className="reward_name"> 실버 커팅볼 스퀘어 체인 여자 팔찌</div>
          <div className="reward_detail">
            <div className="price">12.345원 펀딩</div>
            <div className="rate">107%</div>
            <div className="d_day">D-7</div>
          </div>
        </div>
        <div className="reward_card">
          <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
          <div className="company_name"> 스마트보이</div>
          <div className="reward_name"> 실버 커팅볼 스퀘어 체인 여자 팔찌</div>
          <div className="reward_detail">
            <div className="price">12.345원 펀딩</div>
            <div className="rate">107%</div>
            <div className="d_day">D-7</div>
          </div>
        </div>
        <div className="reward_card">
          <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
          <div className="company_name"> 스마트보이</div>
          <div className="reward_name"> 실버 커팅볼 스퀘어 체인 여자 팔찌</div>
          <div className="reward_detail">
            <div className="price">12.345원 펀딩</div>
            <div className="rate">107%</div>
            <div className="d_day">D-7</div>
          </div>
        </div>
        <div className="reward_card">
          <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
          <div className="company_name"> 스마트보이</div>
          <div className="reward_name"> 실버 커팅볼 스퀘어 체인 여자 팔찌</div>
          <div className="reward_detail">
            <div className="price">12.345원 펀딩</div>
            <div className="rate">107%</div>
            <div className="d_day">D-7</div>
          </div>
        </div>
      </div>
      <button className="reward-more">리워드 프로젝트 더보기</button>
    </div>
  );
};

export default Rewarding;
