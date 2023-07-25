import React, { useState, useEffect } from "react";
import { Route, useNavigate, useLocation } from "react-router-dom";
import "./MakeInvest1.css";
import "./MakeInvestCommon.css";
import { DatePicker } from "antd";
import { PlusCircleOutlined } from "@ant-design/icons";
import { nanoid } from "nanoid";
import axios from "axios";
import "dayjs/locale/zh-cn";
import dayjs from "dayjs";
import Footer from "../../Component/Footer/Footer";
import Header from "../../Component/Header/Header";

const { RangePicker } = DatePicker;

const MakeInvest1 = () => {
  const location = useLocation();
  console.log("location:" + location.state);
  const [totInfo, setTotInfo] = useState({});

  useEffect(() => {
    if (location.state) {
      setTotInfo({ ...location.state.totInfo });

      const file = location.state.totInfo.investRepImgSavedName;
      if (file) {
        const reader = new FileReader();

        reader.onload = (e) => {
          setSelectedImage(e.target.result);
        };
        reader.readAsDataURL(file);
      }
    } else {
      setTotInfo({
        investCategory: "",
        investTargetAmount: 0,
        investProjName: "",
        investRepImg: null,
        investProjKeyword: "",
        investProjDateStart: "",
        investProjDateEnd: "",
        // investVideoUrl: [{ id: nanoid(), url: "" }],
        investVideoUrl: "",
        investContentImg: [],
        investProjContent: "",
        cards: [],
        businessAddress: "",
        bank: "",
        accNumber: "",
        depositorName: "",
        taxBillEmail: "",
        websiteUrl: "",
        facebookUrl: "",
        instagramUrl: "",
        blogUrl: "",
        twitterUrl: "",
        investBusinessLicenseImg: null,
        investBankAccountImg: null,
        useOfFunds: "",
        useOfFundsDateStart: "",
        useOfFundsDateEnd: "",
        rateOfReturn: 0,
        expectedPaymentDate: "",
        repaymentMethod: "",
        investItemIntro: "",
        investItemBusinessValue: "",
        investItemValue: "",
        investItemBenefit: "",
        investEmail: ""
      });
    }
  }, []);

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

  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        setSelectedImage(e.target.result);
      };

      reader.readAsDataURL(file);
      setTotInfo({ ...totInfo, investRepImg: file });
    }
  };

  const handleImageClick = () => {
    document.getElementById("imageUpload").click();
  };

  const navigate = useNavigate(); // useNavigate 추가
  //   const handleNextStep = () => {
  //     console.log(totInfo);
  //     axios
  //       .post("/make-invest/basicinfo", totInfo)
  //       .then(() => {
  //         navigate("/make-invest/moneyinfo", {
  //           state: { totInfo: totInfo },
  //         });
  //       })
  //       .catch((error) => {
  //         console.error(error);
  //       });
  //   };

  const handleNextStep = () => {
    navigate("/makeInvestMoneyinfo", { state: { totInfo: totInfo } });
  };

  return (
    <>
      <Header />
      <div className="investMake-wrapper">
        <div className="proj-progress-div">
          <div className="proj-progress-ing proj-progress-common proj-progress-line">
            1
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
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
          <b>프로젝트의 성공 조건 & 수수료 안내</b>
        </p>
        <p className="custom-font-text">
          프로젝트 종료일 기준 모금액이 목표금액의 100% 이상인 경우에만
          프로젝트가 성공하게 됩니다.
          <br />
          프로젝트가 성공한 경우, 크라우디는 모금액에서 수수료(12%, vat 별도)를
          제한 금액을 정산해드립니다.
        </p>
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>프로젝트의 카테고리를 정해주세요</b>
        </p>
        <select
          className="makeReward-option"
          name="investCategory"
          onChange={handleInputChange}
          value={totInfo.investCategory}
        >
          <option value="none">카테고리를 선택해주세요.</option>
          <option value="테크/가전">테크/가전</option>
          <option value="패션/잡화">패션/잡화</option>
          <option value="홈/리빙">홈/리빙</option>
          <option value="뷰티">뷰티</option>
          <option value="푸드">푸드</option>
          <option value="출판">출판</option>
          <option value="클래스/컨설팅">클래스/컨설팅</option>
          <option value="레저/아웃도어">레저/아웃도어</option>
          <option value="스포츠/모빌리티">스포츠/모빌리티</option>
          <option value="컬쳐/아티스트">컬쳐/아티스트</option>
          <option value="캐릭터/굿즈">캐릭터/굿즈</option>
          <option value="반려동물">반려동물</option>
          <option value="베이비/키즈">베이비/키즈</option>
          <option value="게임/취미">게임/취미</option>
          <option value="여행/숙박">여행/숙박</option>
        </select>
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>프로젝트의 제목을 적어주세요</b>
        </p>
        <p className="custom-font-text">
          프로젝트의 핵심 내용을 담을 수 있는 간결한 제목을 정해주세요.
        </p>
        <div>
          <input
            type="text"
            name="investProjName"
            className="input-box"
            value={totInfo.investProjName}
            onChange={handleInputChange}
          />
        </div>
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>목표 금액을 적어주세요</b>
        </p>
        <p className="custom-font-text">최소 100,000원 이상이어야 합니다.</p>
        <input
          type="text"
          name="investTargetAmount"
          className="input-box"
          placeholder="0"
          value={totInfo.investTargetAmount}
          onChange={handleNumInputChange}
        />{" "}
        &nbsp;원
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>프로젝트의 진행 기간을 적어주세요</b>
        </p>
        <RangePicker
          onChange={(dates, dateStrings) => {
            setTotInfo({
              ...totInfo,
              investProjDateStart: dateStrings[0],
              investProjDateEnd: dateStrings[1]
            });
          }}
          showToday={true}
          allowClear={false}
          value={[
            totInfo.investProjDateStart
              ? dayjs(totInfo.investProjDateStart)
              : null,
            totInfo.investProjDateEnd ? dayjs(totInfo.investProjDateEnd) : null
          ]}
          format="YYYY-MM-DD"
        />
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>프로젝트의 대표 이미지를 등록해주세요</b>
        </p>
        <p className="custom-font-text">
          홈페이지와 외부 공유를 했을 때 보여집니다.
          <br />
          프로젝트를 한 눈에 나타낼 수 있는 이미지를 등록해주세요.
        </p>
        <br />
        <div className="ibi-image-upload">
          <div
            className="ibi-image-upload-info"
            onClick={handleImageClick}
            style={{ cursor: "pointer", width: "35vh", height: "25vh" }}
          >
            {selectedImage ? (
              <img
                src={selectedImage}
                alt="Selected"
                style={{ width: "100%", height: "100%", objectFit: "cover" }}
              />
            ) : (
              <>
                <div style={{ marginBottom: "2%" }}>
                  <PlusCircleOutlined
                    style={{ fontSize: "25px", cursor: "pointer" }}
                  />
                </div>
                최적 사이즈 740*492px
              </>
            )}
          </div>
        </div>
        <input
          type="file"
          id="imageUpload"
          accept="image/*"
          style={{ display: "none" }}
          onChange={handleImageUpload}
        />
        <br />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>프로젝트 메이커명을 적어주세요</b>
        </p>
        <p className="custom-font-text">
          {/* <b style={{ color: "#E93232" }}>(선택사항)</b>   */}
          프로젝트에 노출될 메이커명 또는 기업명을 적어주세요.
        </p>
        <input
          type="text"
          name="investProjKeyword"
          className="input-box"
          value={totInfo.investProjKeyword}
          onChange={handleInputChange}
          // placeholder="키워드, 키워드, 키워드, 키워드, 키워드"
        />
        <div className="button-top-margin"></div>
        <div className="investMake-button-div">
          <button className="investMake-next-button" onClick={handleNextStep}>
            다음 단계{" "}
          </button>
        </div>
        <div className="button-botoom-margin"></div>
      </div>
      <Footer />
    </>
  );
};

export default MakeInvest1;
