import { Swiper, SwiperSlide } from "swiper/react";
import { A11y, Navigation, Scrollbar } from "swiper";
import { LeftOutlined, RightOutlined } from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Swal from "sweetalert2";
import {Calendar} from "antd";

function ContinueProject(props) {
    const [continuedata, setContinue] = useState([]);
    useEffect(() => {
        console.log(props.continueData);
        setContinue(props.continueData); // 전달받은 데이터를 state로 설정
    }, [props.continueData]);
  // useEffect(() => {
  //
  //     axios.get(`http://localhost:8080/rewardList`)
  //         .then(res => {
  //             console.log("성공");
  //             console.log(res.data);
  //             const resData = res.data;
  //             const addData = resData.map(item => {
  //                 const projDateEnd = new Date(item.projDateEnd);
  //                 const today = new Date();
  //                 const differenceInTime = Math.abs(projDateEnd.getTime() - today.getTime());
  //                 const differenceInDays = Math.ceil(differenceInTime / (1000 * 3600 * 24)-1);
  //                 const formattedAmount = item.projTargetAmount.toLocaleString(); // 기본 로케일과 기본 숫자 포맷 사용
  //                 console.log(differenceInDays);
  //                 // item 객체와 차이를 추가하여 새로운 객체를 만듦
  //                 return { ...item, differenceInDays, formattedAmount };
  //             });
  //             setReward(addData);
  //         })
  //         .catch(err => {
  //             console.log(err);
  //         })
  //
  // },[]);
  return (
    <section className="content">
      <div className="content-title">펀딩중</div>
      <div className="content-proj">
        <Swiper
          modules={[Navigation, Scrollbar, A11y]}
          spaceBetween={10}
          slidesPerView={4}
          /*slidesOffsetBefore={30}
          slidesOffsetAfter={-30}*/
          navigation={{
            prevEl: ".prevNav",
            nextEl: ".nextNav",
          }}
        >
          {continuedata.map((item, idx) => {
            return (
              <SwiperSlide className="content-slide">
                <a href={"reward/"+item.id} className="content-item-list-link">
                  <div className="content-item-list">
                    <img
                      src={require("../../assets/images/Project/고고.jpg")}
                      alt=""
                      className="content-item-img"
                    />
                    <div className="content-item">
                      <div className="corP">KOSTA</div>

                        <div className="">
                          <span className="fdrate">{item.formattedPaymentamountPercent}% 달성</span>
                          <span className="fdprice">{item.formattedPaymentamountSum}원</span>
                          <span className="dday"> {item.differenceInDays != 0 ? "D-"+item.differenceInDays : '오늘마감'}</span>
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
          <LeftOutlined className="prevNav" />
          <RightOutlined className="nextNav" />
        </div>
      </div>
    </section>
  );
}

export default ContinueProject;
