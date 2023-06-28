import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/effect-fade";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "../../pages/Reward/Reward.css";

import { Autoplay, EffectFade, Navigation, Pagination } from "swiper";

const items = [
  { src: "/assets/imgs/banner1.png" },
  { src: "/assets/imgs/banner2.png" },
  { src: "/assets/imgs/banner3.png" },
];

const Banner = () => {
  return (
    <>
      <Swiper
        effect={"fade"}
        autoplay={{
          delay: 3000,
          disabledOnInteraction: false,
        }}
        pagination={{
          clickable: true,
        }}
        modules={[Navigation, EffectFade, Pagination, Autoplay]}
        className="mySwiper"
        loop={true}
      >
        {items.map((item, idx) => {
          return (
            <SwiperSlide key={idx}>
              <img src={item.src} />
            </SwiperSlide>
          );
        })}
      </Swiper>
    </>
  );
};

export default Banner;
