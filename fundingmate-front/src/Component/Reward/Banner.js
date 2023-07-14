import React, { useEffect } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/swiper-bundle.min.css";
import "swiper/swiper.min.css";
import "swiper/components/pagination/pagination.min.css";
import "swiper/components/scrollbar/scrollbar.min.css";

import SwiperCore, { Autoplay, EffectFade, Pagination } from "swiper";

SwiperCore.use([Autoplay, EffectFade, Pagination]);

const items = [
  { src: "/assets/imgs/banner1.png" },
  { src: "/assets/imgs/banner2.png" },
  { src: "/assets/imgs/banner3.png" },
];

const Banner = () => {
  useEffect(() => {
    const swiper = new Swiper(".mySwiper", {
      effect: "fade",
      autoplay: {
        delay: 3000,
        disableOnInteraction: false,
      },
      pagination: {
        clickable: true,
      },
      loop: true,
    });

    return () => {
      // Cleanup Swiper instance
      swiper.destroy();
    };
  }, []);

  return (
    <Swiper
      className="mySwiper"
      spaceBetween={0}
      slidesPerView={1}
      onSlideChange={() => {}}
      onSwiper={() => {}}
    >
      {items.map((item, idx) => (
        <SwiperSlide key={idx}>
          <img
            src={item.src}
            className="banner_img"
            alt={`Banner ${idx + 1}`}
          />
        </SwiperSlide>
      ))}
    </Swiper>
  );
};

export default Banner;
