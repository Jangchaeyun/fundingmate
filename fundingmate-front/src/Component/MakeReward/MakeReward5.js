import React, { useState, useEffect } from "react";

import { useNavigate, useLocation, useParams } from "react-router-dom";

import "./MakeReward5.css";
import "./MakeRewardCommon.css";
import {
  PlusCircleOutlined,
  IeOutlined,
  FacebookOutlined,
  InstagramOutlined,
  BoldOutlined,
  TwitterOutlined
} from "@ant-design/icons";
import DaumPostcode from "react-daum-postcode";
import { Modal } from "antd";
import axios from "axios";
import { useSelector, useDispatch } from "react-redux";
import CorFooter from "../../Component/Footer/CorFooter";
import Header from "../../Component/Header/Header";
import Story from "../RewardDetail/Detail/Story";
import Footer from "../../Component/Footer/Footer";
const MakeReward5 = () => {
  const location = useLocation();
  const preTotInfo = location.state.totInfo;
  const [totInfo, setTotInfo] = useState(preTotInfo);
  const [rewardInfo, setRewardInfo] = useState(null);

  const { rewardId } = useParams();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setTotInfo((prevTotInfo) => ({ ...prevTotInfo, [name]: value }));
  };

  const regexPattern = /^[0-9]*$/; // 숫자만 입력되도록 정규식 패턴 설정
  const handleNumInputChange = (e) => {
    const { name, value } = e.target;
    if (regexPattern.test(value)) {
      setTotInfo((prevTotInfo) => ({ ...prevTotInfo, [name]: value }));
    }
  };

  const [selectedImage, setSelectedImage] = useState(null);
  const [selectedImage2, setSelectedImage2] = useState(null);

  useEffect(() => {
    let file = totInfo.rewardIdBusinessLicenseImgSavedName;
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setSelectedImage(e.target.result);
      };
      reader.readAsDataURL(file);
    }
    file = totInfo.rewardBankAccountCopyImgSavedName;
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setSelectedImage2(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  }, []);

  const uploadImageToServer = async (file) => {
    try {
      const formData = new FormData();
      formData.append("file", file);

      await axios.post("http://localhost:8080/upload-image", formData);
      console.log("Image uploaded successfully!");
    } catch (error) {
      console.log("Error uploading image:", error);
    }
  };

  const handleImageUpload = (event) => {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        setSelectedImage(e.target.result);
      };

      reader.readAsDataURL(file);
      setTotInfo({ ...totInfo, rewardBusinessLicenseImg: file });
      //uploadImageToServer(file);
    }
  };

  const handleImageClick = () => {
    document.getElementById("imageUpload").click();
  };

  const handleImageUpload2 = (event) => {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        setSelectedImage2(e.target.result);
      };

      reader.readAsDataURL(file);
      setTotInfo({ ...totInfo, rewardBankAccountCopyImg: file });
      console.log(file.src);
    }
  };

  const handleImageClick2 = () => {
    document.getElementById("imageUpload2").click();
  };

  const handleComplete = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";

    const { addressType, bname, buildingName } = data;
    if (addressType === "R") {
      if (bname !== "") {
        extraAddress += bname;
      }
      if (buildingName !== "") {
        extraAddress += `${extraAddress !== "" && ", "}${buildingName}`;
      }
      fullAddress += `${extraAddress !== "" ? ` ${extraAddress}` : ""}`;
    }

    setTotInfo({ ...totInfo, businessAddress: fullAddress });

    setShowModal(false); // Close the modal
  };

  const handleAddressSearch = () => {
    setShowModal(true);
  };
  const [showModal, setShowModal] = useState(false);
  const navigateToStep1 = useNavigate();
  const navigateToStep2 = useNavigate();

  const handlePreviousStep = () => {
    navigateToStep1("/makeRewardGoodsinfo", { state: { totInfo: totInfo } });
  };

  const userId = useSelector((state) => state.Id);

  const handleNextStep = () => {
    const convertToFilesDTO = (files) => {
      if (files.length === 0) {
        return null; // 빈 배열인 경우 null로 설정
      }
      return {
        fileId: null,
        fileName: files[0].name, // 첫 번째 파일의 이름 사용
        fileRegistrationDate: null
        // 필요한 경우 다른 필드를 추가하거나 변경할 수 있습니다.
      };
    };

    const convertToOneFilesDTO = (files) => {
      if (!files || files.length === 0) {
        return null; // 파일이 없는 경우 null로 설정
      }

      return {
        fileId: null,
        fileName: files.name, // 첫 번째 파일의 이름 사용
        fileRegistrationDate: null
        // 필요한 경우 다른 필드를 추가하거나 변경할 수 있습니다.
      };
    };

    // const requestData = {
    //   ...totInfo,
    //   rewardTypes: totInfo.cards,
    //   rewardContentImgSavedName: convertToFilesDTO(
    //     totInfo.rewardContentImgSavedName
    //   ),
    //   rewardRepImgSavedName: convertToOneFilesDTO(
    //     totInfo.rewardRepImgSavedName
    //   ),
    //   rewardIdBusinessLicenseImgSavedName: convertToOneFilesDTO(
    //     totInfo.rewardIdBusinessLicenseImgSavedName
    //   ),
    //   rewardBankAccountCopyImgSavedName: convertToOneFilesDTO(
    //     totInfo.rewardBankAccountCopyImgSavedName
    //   ),
    // };

    let formData = new FormData();
    formData.append("userId", userId);
    formData.append("rewardCategory", totInfo.rewardCategory);
    formData.append("projTargetAmount", totInfo.projTargetAmount);
    formData.append("projName", totInfo.projName);

    formData.append("projKeyWord", totInfo.projKeyWord);
    formData.append("projDateStart", totInfo.projDateStart);
    formData.append("projDateEnd", totInfo.projDateEnd);
    formData.append("rewardVideoAddress", totInfo.rewardVideoAddress);
    formData.append(
      "rewardRefundExchangePolicy",
      totInfo.rewardRefundExchangePolicy
    );
    formData.append("projContent", totInfo.projContent);

    //cards 의 option id 값 초기화
    let tcards = totInfo.cards;
    for (let i = 0; i < tcards.length; i++) {
      for (let j = 0; j < tcards[i].options.length; j++) {
        tcards[i].options[j].id = null;
      }
    }

    formData.append("cards", JSON.stringify(tcards));

    formData.append("rewardContact", totInfo.rewardContact);
    formData.append("rewardEmail", totInfo.rewardEmail);
    formData.append("modelName", totInfo.modelName);
    formData.append("rewardLaw", totInfo.rewardLaw);
    formData.append("countryOfOrigin", totInfo.countryOfOrigin);
    formData.append("manufacturer", totInfo.manufacturer);
    formData.append("asPhoneNumber", totInfo.asPhoneNumber);

    formData.append("businessAddress", totInfo.businessAddress);
    formData.append("bank", totInfo.bank);
    formData.append("accNumber", totInfo.accNumber);
    formData.append("depositorName", totInfo.depositorName);
    formData.append("taxBillEmail", totInfo.taxBillEmail);
    formData.append("websiteUrl", totInfo.websiteUrl);
    formData.append("facebookUrl", totInfo.facebookUrl);
    formData.append("instagramUrl", totInfo.instagramUrl);
    formData.append("blogUrl", totInfo.blogUrl);
    formData.append("twitterUrl", totInfo.twitterUrl);

    formData.append("rewardRepImg", totInfo.rewardRepImg);
    for (let i = 0; i <= totInfo.rewardContentImg.length; i++) {
      formData.append("rewardContentImg", totInfo.rewardContentImg[i]);
    }
    formData.append(
      "rewardBusinessLicenseImg",
      totInfo.rewardBusinessLicenseImg
    );
    formData.append(
      "rewardBankAccountCopyImg",
      totInfo.rewardBankAccountCopyImg
    );

    console.log(totInfo);
    axios
      .post("http://localhost:8080/makeReward", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
      .then((response) => {
        console.log(response.data);
        const rewardId = response.data;
        setRewardInfo(totInfo);
        alert("프로젝트가 등록되었습니다.");
        navigateToStep2(`/rewarddetail/story/${rewardId}`);
      })
      .catch((error) => {
        console.error(error);
        alert("프로젝트를 완성해주세요.");
      });
  };

  return (
    <>
      <Header />
      <div className="investMake-wrapper">
        <div className="proj-progress-div">
          <div className="proj-progress proj-progress-common proj-progress-line">
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
          <div className="proj-progress-ing proj-progress-common">5</div>
        </div>

        <p className="custom-font-title">
          <b>진행자 정보를 입력해 주세요</b>
        </p>
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>사업자등록증 이미지를 올려주세요(개인일 경우 신분증 이미지)</b>
        </p>

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
                이미지 추가하기
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

        <p className="custom-font-sub-title">
          <b>사업자 주소를 선택해 주세요(개인일 경우 신분증 상의 주소)</b>
        </p>

        <input
          onChange={handleInputChange}
          type="text"
          name="businessAddress"
          className="input-box"
          value={totInfo.businessAddress}
          readOnly
        />
        {/* 주소 찾기 버튼 */}
        <button onClick={handleAddressSearch} className="address-button">
          주소 찾기
        </button>

        {/* DaumPostcode 컴포넌트 */}
        {showModal && (
          <Modal
            title="주소 찾기"
            visible={showModal}
            onCancel={() => setShowModal(false)}
            footer={null}
          >
            <DaumPostcode onComplete={handleComplete} />
          </Modal>
        )}
        <br />
        <br />
        <br />
        <br />
        <p className="custom-font-title">
          <b>계좌 정보를 입력해 주세요</b>
        </p>

        <p className="custom-font-text">
          프로젝트 종료 후 정산 받을 계좌를 입력해 주세요.
        </p>
        <br />
        <br />

        <p className="custom-font-sub-title">
          <b>은행과 계좌 번호를 적어주세요</b>
        </p>

        <select
          className="bank-name"
          name="bank"
          onChange={handleInputChange}
          value={totInfo.bank}
        >
          <option value="none">은행을 선택해주세요.</option>
          <option value="산업은행">산업은행</option>
          <option value="기업은행">기업은행</option>
          <option value="국민은행">국민은행</option>
          <option value="외환은행">외환은행</option>
          <option value="수협">수협</option>
          <option value="유안타증권">유안타증권</option>
          <option value="농협">농협</option>
          <option value="레우리은행">우리은행</option>
          <option value="SC은행">SC은행</option>
          <option value="컬삼성증권">삼성증권</option>
          <option value="한국씨티은행">한국씨티은행</option>
          <option value="대구은행">대구은행</option>
          <option value="부산은행">부산은행</option>
          <option value="광주은행">광주은행</option>
          <option value="제주은행">제주은행</option>
          <option value="전북은행">전북은행</option>
          <option value="경남은행">경남은행</option>
          <option value="새마을금고">새마을금고</option>
          <option value="신협">신협</option>
          <option value="상호저축은행">상호저축은행</option>
          <option value="우체국">우체국</option>
          <option value="하나은행">하나은행</option>
          <option value="신한은행">신한은행</option>
          <option value="케이뱅크">케이뱅크</option>
          <option value="카카오뱅크">카카오뱅크</option>
        </select>
        <input
          type="text"
          name="accNumber"
          value={totInfo.accNumber}
          onChange={handleNumInputChange}
          className="bank-num"
          placeholder="계좌 번호를 숫자만 입력해 주세요.(-제외)"
        />
        <br />
        <br />

        <p className="custom-font-sub-title">
          <b>예금주명을 적어주세요</b>
        </p>
        <input
          type="text"
          name="depositorName"
          value={totInfo.depositorName}
          onChange={handleInputChange}
          className="input-box"
          placeholder="계좌에 등록된 예금주명과 일치해야 합니다."
        />
        <br />
        <br />
        <p className="custom-font-sub-title">
          <b>통장 사본 이미지를 올려주세요</b>
        </p>

        <div className="ibi-image-upload">
          <div
            className="ibi-image-upload-info"
            onClick={handleImageClick2}
            style={{ cursor: "pointer", width: "35vh", height: "25vh" }}
          >
            {selectedImage2 ? (
              <img
                src={selectedImage2}
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
                이미지 추가하기
              </>
            )}
          </div>
        </div>
        <input
          type="file"
          id="imageUpload2"
          accept="image/*"
          style={{ display: "none" }}
          onChange={handleImageUpload2}
        />
        <br />
        <br />

        <p className="custom-font-sub-title">
          <b>세금계산서를 발급 받을 이메일을 적어주세요</b>
        </p>

        <input
          type="text"
          name="taxBillEmail"
          value={totInfo.taxBillEmail}
          onChange={handleInputChange}
          className="input-box"
        />

        <br />
        <br />
        <br />
        <br />
        <hr />
        <br />
        <br />
        <br />

        <p className="custom-font-title">
          <b>홈페이지와 SNS주소를 적어주세요</b>
        </p>

        <p className="custom-font-text">
          <b style={{ color: "#E93232" }}>(선택사항)</b>현재 운영 중인 곳의
          주소를 적어주세요. 진행자 정보에 노출됩니다.
        </p>
        <div style={{ display: "flex", flexDirection: "column" }}>
          <div className="sns-address-div">
            <div className="snsAddress">
              <IeOutlined />
            </div>
            <input
              type="text"
              name="websiteUrl"
              value={totInfo.websiteUrl}
              onChange={handleInputChange}
              className="sns-input-box"
              placeholder="주소를 입력해주세요."
            />
          </div>
          <div className="sns-address-div">
            <div className="snsAddress">
              <FacebookOutlined />
            </div>
            <input
              type="text"
              name="facebookUrl"
              value={totInfo.facebookUrl}
              onChange={handleInputChange}
              className="sns-input-box"
              placeholder="주소를 입력해주세요."
            />
          </div>
          <div className="sns-address-div">
            <div className="snsAddress">
              <InstagramOutlined />
            </div>
            <input
              type="text"
              name="instagramUrl"
              value={totInfo.instagramUrl}
              onChange={handleInputChange}
              className="sns-input-box"
              placeholder="주소를 입력해주세요."
            />
          </div>
          <div className="sns-address-div">
            <div className="snsAddress">
              <BoldOutlined />
            </div>
            <input
              type="text"
              name="blogUrl"
              value={totInfo.blogUrl}
              onChange={handleInputChange}
              className="sns-input-box"
              placeholder="주소를 입력해주세요."
            />
          </div>
          <div className="sns-address-div">
            <div className="snsAddress">
              <TwitterOutlined />
            </div>
            <input
              type="text"
              name="twitterUrl"
              value={totInfo.twitterUrl}
              onChange={handleInputChange}
              className="sns-input-box"
              placeholder="주소를 입력해주세요."
            />
          </div>
        </div>
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
            저장하기{" "}
          </button>
        </div>
        <div className="button-botoom-margin"></div>
      </div>
      <Footer />
    </>
  );
};
export default MakeReward5;
