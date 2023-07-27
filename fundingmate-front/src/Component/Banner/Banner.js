import "./Banner.css";
import { Swiper, SwiperSlide } from "swiper/react";
import {
  A11y,
  Navigation,
  Pagination,
  Scrollbar,
  Autoplay,
} from "swiper"; // 추가
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import SwiperCore from "swiper";
import {useEffect, useState} from "react";
import axios from "axios";

function Banner(props) {
  const [banner, setBanner] = useState([]);
  SwiperCore.use([Autoplay]);
  useEffect(() => {
    setBanner(props.bannerData);
  },[props.bannerData])
  // const getAllBanner = () => {
  //   axios.get(`http://localhost:8080/bannerList`)
  //       .then(res => {
  //         console.log(res.data);
  //         setBanner([...res.data]);
  //       })
  //       .catch(err => {
  //         console.log(err);
  //       })
  // }
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
                '<span class="banner-pagination"><span class="' + currentClass + '">currentClass</span> / ' +
                '<span class="' + totalClass + '">totalClass</span></span>'
              );
            },
          }}
          autoplay={{ delay: 3000, disableOnInteraction: false }}
          loop={true}
        >
          {banner.length !== 0 && banner.map((banner,index) => {
              return (
                  <SwiperSlide className="banner-slide" key={banner.id}>
                    <a href={banner.bn_link} className="banner-link">
                      <div className="banner-wrap" >
                          <div className="banner-info">
                              <div className="banner-title">
                                  {banner.bn_title}
                              </div>
                              <div className="banner-content">
                                  {banner.bn_content}
                              </div>
                          </div>
                      </div>
                      <img
                          className="banner-img"
                          src={require(`../../assets/images/Banner/${banner.fileSavedName}`)}
                          alt="배너1"
                          // width={"100%"}
                          // height={"300px"}
                      />
                    </a>
                  </SwiperSlide>
              )
          })}
          {/*<SwiperSlide className="banner-slide">*/}
          {/*  <a href="#!" className="banner-link">*/}
          {/*    <img*/}
          {/*      className="banner-img"*/}
          {/*      src={require("../../assets/images/Banner/고고.jpg")}*/}
          {/*      alt="배너1"*/}
          {/*      // width={"100%"}*/}
          {/*      // height={"300px"}*/}
          {/*    />*/}
          {/*  </a>*/}
          {/*</SwiperSlide>*/}
          {/*<SwiperSlide className="banner-slide">*/}
          {/*  <a href="#!" className="banner-link">*/}
          {/*    <img*/}
          {/*      className="banner-img"*/}
          {/*      src={require("../../assets/images/Banner/고고.jpg")}*/}
          {/*      alt="배너1"*/}
          {/*      width={"100%"}*/}
          {/*      height={"300px"}*/}
          {/*    />*/}
          {/*  </a>*/}
          {/*</SwiperSlide>*/}
          {/*<SwiperSlide className="banner-slide">*/}
          {/*  <a href="#!" className="banner-link">*/}
          {/*    <img*/}
          {/*      className="banner-img"*/}
          {/*      src={require("../../assets/images/Banner/고고.jpg")}*/}
          {/*      alt="배너1"*/}
          {/*      width={"100%"}*/}
          {/*      height={"300px"}*/}
          {/*    />*/}
          {/*  </a>*/}
          {/*</SwiperSlide>*/}
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
