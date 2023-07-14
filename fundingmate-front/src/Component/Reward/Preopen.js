import React from "react";
import "../../pages/Reward/Reward.css";
import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/swiper-bundle.min.css";
import "swiper/swiper.min.css";
import "swiper/components/navigation/navigation.min.css";
import "swiper/components/pagination/pagination.min.css";
import "swiper/components/scrollbar/scrollbar.min.css";

const Preopen = () => {
  return (
    <div className="reward">
      <p className="reward_title">사전 공개</p>
      <div className="rewarding_proj">
        곧 오픈할 프로젝트들을 가장 먼저 만나보세요.
      </div>
      <div className="reward_cards">
        <Swiper
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          spaceBetween={6}
          slidesPerView={4}
          navigation
        >
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="reward_card">
              <img src="/assets/imgs/bracelet.jpg" className="reward_img" />
              <div className="com_name"> 스마트보이</div>
              <div className="reward_name">
                실버 커팅볼 스퀘어 체인 여자 팔찌
              </div>
              <div className="reward_detail">
                <div className="price">12.345원 펀딩</div>
                <div className="rate">107%</div>
                <div className="d_day">D-7</div>
              </div>
            </div>
            <div className="bell_box">알 림 신 청</div>
          </SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
};

export default Preopen;
