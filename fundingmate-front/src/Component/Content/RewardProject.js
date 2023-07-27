import { Swiper, SwiperSlide } from "swiper/react";
import { A11y, Navigation, Scrollbar } from "swiper";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Swal from "sweetalert2";
import { Calendar } from "antd";

function RewardProject(props) {
  const [reward, setReward] = useState([]);
  useEffect(() => {
    console.log(props.rewardData);
    setReward(props.rewardData); // 전달받은 데이터를 state로 설정
  }, [props.rewardData]);

  return (
    <section className="content">
      <div className="content-title">
        리워드 프로젝트
        <a href="reward">
          프로젝트 더보기 <RightOutlined />
        </a>
      </div>
      <div className="content-proj">
        <Swiper
          modules={[Navigation, Scrollbar, A11y]}
          spaceBetween={10}
          slidesPerView={4}
          navigation={{
            prevEl: ".rewardprevNav",
            nextEl: ".rewardnextNav"
          }}
        >
          {reward.map((item, idx) => {
            return (
              <SwiperSlide className="content-slide">
                <a
                  href={"rewarddetail/story/" + item.id}
                  className="content-item-list-link"
                >
                  <div className="content-item-list">
                    <img
                      src={`http://localhost:8080/img/${item.rewardRepImgSavedName}`}
                      alt=""
                      className="content-item-img"
                    />
                    <div className="content-item">
                      <div className="corP">{item.projKeyWord}</div>

                      <div className="">
                        <span className="fdrate">
                          {item.formattedPaymentamountPercent}% 달성
                        </span>
                        <span className="fdprice">
                          {item.formattedPaymentamountSum}원
                        </span>
                        <span className="dday">
                          {" "}
                          {item.differenceInDays != 0
                            ? "D-" + item.differenceInDays
                            : "오늘마감"}
                        </span>
                      </div>

                      <div className="proN">{item.projName}</div>
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
        </Swiper>
        <div className="projNav">
          <LeftOutlined className="prevNav rewardprevNav" />
          <RightOutlined className="nextNav rewardnextNav" />
        </div>
      </div>
    </section>
  );
}

export default RewardProject;
