import React from "react";
import "./Banner.css";
import { Swiper, SwiperSlide } from "swiper/react";
import { A11y, Navigation, Pagination, Scrollbar } from "swiper"; // 추가
import "swiper/swiper-bundle.min.css";
import "swiper/swiper.min.css";
import "swiper/components/navigation/navigation.min.css";
import "swiper/components/pagination/pagination.min.css";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import SwiperCore, { EffectCoverflow, Autoplay } from "swiper";

function Banner() {
  SwiperCore.use([Autoplay]);
  return (
    <div className="banner">
      <div className="banner-slide">
        <Swiper
          style={{
            "--swiper-navigation-size": "25px",
          }}
          className="banner-slide-swiper"
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          spaceBetween={10}
          slidesPerView={1}
          navigation={{
            prevEl: ".bannerPrevNav",
            nextEl: ".bannerNextNav",
          }}
          pagination={{
            type: "fraction",
            renderFraction: function (currentClass, totalClass) {
              // type이 fraction일 때 사용
              return (
                '<span class="banner-pagination"><span class="' +
                currentClass +
                '">currentClass</span> / ' +
                '<span class="' +
                totalClass +
                '">totalClass</span></span>'
              );
            },
          }}
          autoplay={{ delay: 2000, disableOnInteraction: false }}
          loop={true}
        >
          <SwiperSlide className="banner-slide">
            <a href="#!" className="banner-link">
              <img
                className="banner-img"
                src={require("../../assets/images/Banner/고고.jpg")}
                alt="배너1"
                // width={"100%"}
                // height={"300px"}
              />
            </a>
          </SwiperSlide>
          <SwiperSlide className="banner-slide">
            <a href="#!" className="banner-link">
              <img
                className="banner-img"
                src={require("../../assets/images/Banner/고고.jpg")}
                alt="배너1"
                width={"100%"}
                height={"300px"}
              />
            </a>
          </SwiperSlide>
          <SwiperSlide className="banner-slide">
            <a href="#!" className="banner-link">
              <img
                className="banner-img"
                src={require("../../assets/images/Banner/고고.jpg")}
                alt="배너1"
                width={"100%"}
                height={"300px"}
              />
            </a>
          </SwiperSlide>
        </Swiper>
        <div className="bannerNav">
          <LeftOutlined className="bannerPrevNav" />
          <RightOutlined className="bannerNextNav" />
        </div>
      </div>
    </div>
  );
}

export default Banner;
