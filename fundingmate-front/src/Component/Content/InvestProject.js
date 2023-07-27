import { Swiper, SwiperSlide } from "swiper/react";
import { A11y, Navigation, Scrollbar } from "swiper";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Swal from "sweetalert2";
import { Calendar } from "antd";

function InvestProject(props) {
  const [invest, setInvest] = useState([]);
  useEffect(() => {
    console.log(props.investData);
    setInvest(props.investData); // 전달받은 데이터를 state로 설정
  }, [props.investData]);
  return (
    <section className="content">
      <div className="content-title">
        투자형 프로젝트
        <a href="fund">
          프로젝트 더보기 <RightOutlined />
        </a>
      </div>
      <div className="content-proj">
        <Swiper
          modules={[Navigation, Scrollbar, A11y]}
          spaceBetween={10}
          slidesPerView={4}
          /*slidesOffsetBefore={30}
          slidesOffsetAfter={-30}*/
          navigation={{
            prevEl: ".investprevNav",
            nextEl: ".investnextNav"
          }}
        >
          {invest.map((item, idx) => {
            return (
              <SwiperSlide className="content-slide">
                <a
                  // href={"fund-detail/story/" + item.id}
                  href={"fund-detail/story/"}
                  className="content-item-list-link"
                >
                  <div className="content-item-list">
                    <img
                      src={`http://localhost:8080/img/${item.investRepImgSavedName}`}
                      alt=""
                      className="content-item-img"
                    />
                    <div className="content-item">
                      <div className="corP">{item.investProjKeyword}</div>

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

                      <div className="proN">{item.investProjName}</div>
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
          <LeftOutlined className="prevNav investprevNav" />
          <RightOutlined className="nextNav investnextNav" />
        </div>
      </div>
    </section>
  );
}

export default InvestProject;
