import React, { useState, useEffect } from "react";
import { Route, useNavigate, useLocation } from "react-router-dom";
import "./MakeInvest2.css";
import "./MakeInvestCommon.css";
import { DatePicker } from "antd";
import "dayjs/locale/zh-cn";
import dayjs from "dayjs";
import axios from "axios";
import Footer from "../../Component/Footer/Footer";
import Header from "../../Component/Header/Header";

const { RangePicker } = DatePicker;

const MakeInvest2 = () => {
  const location = useLocation();
  const preTotInfo = location.state.totInfo;
  const [totInfo, setTotInfo] = useState(preTotInfo);

  const handleInputChange = (e) => {
    setTotInfo({ ...totInfo, [e.target.name]: e.target.value });
  };

  const regexPattern = /^[0-9]*$/; // 숫자만 입력되도록 정규식 패턴 설정
  const handleNumInputChange = (e) => {
    const { name, value } = e.target;

    // 숫자만 입력되도록 검증
    if (regexPattern.test(value)) {
      setTotInfo({ ...totInfo, [name]: value });
    }
  };

  const navigateToStep1 = useNavigate();
  const navigateToStep2 = useNavigate();

  const handlePreviousStep = () => {
    navigateToStep1("/makeInvestBasicinfo", { state: { totInfo: totInfo } });
  };

  //   const handleNextStep = () => {
  //     axios
  //       .post("/make-invest/moneyinfo", totInfo) // 서버의 API 엔드포인트에 맞게 경로와 데이터를 수정해야 합니다.
  //       .then(() => {
  //         navigateToStep2("/make-invest/story", {
  //           state: { totInfo: totInfo },
  //         });
  //       })
  //       .catch((error) => {
  //         console.error(error);
  //       });
  //   };

  const handleNextStep = () => {
    navigateToStep2("/makeInvestStory", { state: { totInfo: totInfo } });
  };

  return (
    <>
      <Header />
      <div className="investMake-wrapper">
        <div className="proj-progress-div">
          <div className="proj-progress proj-progress-common proj-progress-line">
            1
          </div>
          <div className="proj-progress-ing proj-progress-common proj-progress-line">
            2
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
            3
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
            4
          </div>
          <div className="proj-progress proj-progress-common">5</div>
        </div>
        <p className="custom-font-title">
          <b>프로젝트 정보</b>
        </p>
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>자금용도</b>
        </p>
        <input
          type="text"
          name="useOfFunds"
          className="input-box"
          value={totInfo.useOfFunds}
          onChange={handleInputChange}
        />
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>자금 사용 기간</b>
        </p>
        <RangePicker
          onChange={(dates, dateStrings) => {
            setTotInfo({
              ...totInfo,
              useOfFundsDateStart: dateStrings[0],
              useOfFundsDateEnd: dateStrings[1]
            });
          }}
          showToday={true}
          allowClear={false}
          value={[
            totInfo.useOfFundsDateStart
              ? dayjs(totInfo.useOfFundsDateStart)
              : null,
            totInfo.useOfFundsDateEnd ? dayjs(totInfo.useOfFundsDateEnd) : null
          ]}
          format="YYYY-MM-DD"
        />
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>수익률</b>
        </p>
        <input
          type="text"
          name="rateOfReturn"
          className="input-box"
          value={totInfo.rateOfReturn}
          onChange={handleNumInputChange}
        />
        &nbsp;%
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>예상 상환 실행일</b>
        </p>
        <DatePicker
          style={{ width: "240px", height: "30px" }}
          id="rew-date-picker"
          showToday={true}
          allowClear={false}
          format={"YYYY-MM-DD"}
          value={
            totInfo.expectedPaymentDate
              ? dayjs(totInfo.expectedPaymentDate)
              : null
          }
          name="expectedPaymentDate"
          onChange={(value, dateString) => {
            setTotInfo({ ...totInfo, expectedPaymentDate: dateString });
          }}
        />
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>상환 방식</b>
        </p>
        <input
          type="text"
          name="repaymentMethod"
          className="input-box"
          value={totInfo.repaymentMethod}
          onChange={handleInputChange}
        />
        <div className="button-top-margin"></div>
        <div className="investMake-button-div">
          <button
            className="investMake-prev-button"
            onClick={handlePreviousStep}
          >
            {" "}
            이전 단계{" "}
          </button>
          <button className="investMake-next-button" onClick={handleNextStep}>
            {" "}
            다음 단계{" "}
          </button>
        </div>
        <div className="button-botoom-margin"></div>
      </div>
      <Footer />
    </>
  );
};
export default MakeInvest2;
