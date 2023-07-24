import { Swiper, SwiperSlide } from "swiper/react";
import { A11y, Navigation, Scrollbar } from "swiper";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Swal from "sweetalert2";

function Project(p) {
    const [reword, setResord] = useState([]);
    const [visibleRewards, setVisibleRewards] = useState(4);
  const items = [{}, {}, {}, {}, {}, {}];
  useEffect(() => {
      axios.get(`http://localhost:8080/reward/find/finishreward/more`,
          {
              params: {
                  startIndex: 0,
                  endIndex: visibleRewards
              },
          })
          .then(res => {
              console.log(res.data);
              setResord([...res.data]);
          })
          .catch(err => {
              console.log(err);
          })
  })
  return (
    <section className="content">
      <div className="content-title">{p.title}</div>
      <div className="content-proj">
        <Swiper
          modules={[Navigation, Scrollbar, A11y]}
          spaceBetween={0}
          slidesPerView={3}
          slidesOffsetBefore={30}
          slidesOffsetAfter={-30}
          navigation={{
            prevEl: ".prevNav",
            nextEl: ".nextNav",
          }}
        >
          {items.map((item, idx) => {
            return (
              <SwiperSlide className="content-slide">
                <a href="#!" className="content-item-list-link">
                  <div className="content-item-list">
                    <img
                      src={require("../../assets/images/Banner/고고.jpg")}
                      alt=""
                      className="content-item-img"
                    />
                    <div className="content-item">
                      <div className="corP">KOSTA</div>
                      {p.title != "사전공개" ? (
                        <div className="">
                          <span className="fdrate">10,000% 펀딩달성</span>
                          <span className="fdprice">1,000원</span>
                          <span className="dday">D-00</span>
                        </div>
                      ) : (
                        <div className="Commingsoon">오픈예정</div>
                      )}
                      <div className="proN">KOSTA 프로젝트 펀딩 받아여</div>
                      <div className="fundGo">
                        지금 펀딩 참여하기
                        <RightOutlined />
                      </div>
                    </div>
                  </div>
                </a>
              </SwiperSlide>
            );
          })}
          <LeftOutlined className="prevNav" />
          <RightOutlined className="nextNav" />
        </Swiper>
      </div>
    </section>
  );
}

export default Project;
