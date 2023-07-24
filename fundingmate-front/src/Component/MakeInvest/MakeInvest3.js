import React, { useRef, useState, useEffect } from "react";
import { Route, useNavigate, useLocation } from "react-router-dom";
import "./MakeInvest3.css";
import "./MakeInvestCommon.css";
import axios from "axios";
import {
  PlusCircleOutlined,
  PlusSquareOutlined,
  MinusCircleOutlined,
  MinusSquareOutlined
} from "@ant-design/icons";

import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
import "@toast-ui/editor/dist/i18n/ko-kr";
import { nanoid } from "nanoid";
import Footer from "../../Component/Footer/Footer";
import Header from "../../Component/Header/Header";
const MAX_IMAGES = 15;

const MakeInvest3 = () => {
  const location = useLocation();
  const preTotInfo = location.state.totInfo;
  const [totInfo, setTotInfo] = useState(preTotInfo);
  const [images, setImages] = useState([]);
  const handleInputChange = (e) => {
    setTotInfo({ ...totInfo, [e.target.name]: e.target.value });
  };
  const editorRef = useRef();
  const onChange = () => {
    const data = editorRef.current.getInstance().getHTML();
  };

  const handleToastChange = (e) => {
    const data = editorRef.current.getInstance().getHTML();
    setTotInfo({ ...totInfo, investProjContent: data });
  };

  const [inputs, setInputs] = useState([{ id: 1 }]);

  const handleAddInput = () => {
    const newId = nanoid();
    const newInput = { id: newId };
    setTotInfo({
      ...totInfo,
      investVideoUrl: [...totInfo.investVideoUrl, newInput]
    });
    //setInputs((prevInputs) => [...prevInputs, newInput]);
  };

  const handleDeleteInput = (id) => {
    const delinputs = totInfo.investVideoUrl.filter((input) => input.id !== id);
    setTotInfo({ ...totInfo, investVideoUrl: [...delinputs] });
  };
  const [showDeleteIcon, setShowDeleteIcon] = useState(false);
  //const [images, setImages] = useState([]);
  const [showDeleteButton, setShowDeleteButton] = useState(false);

  const handleImageInit = (event) => {
    if (event.target.files.length > 0) event.target.value = "";
  };

  useEffect(() => {
    console.log(totInfo);
    let imgList = [];
    for (let file of totInfo.investContentImg) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const imageCard = {
          src: e.target.result,
          alt: "Selected",
          style: { width: "100%", height: "100%", objectFit: "cover" }
        };
        imgList.push(imageCard);
        setImages([...imgList]);
        console.log(imgList.length);
      };
      // reader가 이미지 읽도록 하기
      reader.readAsDataURL(file);
    }
  }, []);
  const handleImageUpload = (event) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      // 이미지가 로드가 된 경우
      reader.onload = (e) => {
        const imageCard = {
          src: e.target.result,
          alt: "Selected",
          style: { width: "100%", height: "100%", objectFit: "cover" }
        };
        setImages([...images, imageCard]);
        setTotInfo({
          ...totInfo,
          investContentImg: [...totInfo.investContentImg, event.target.files[0]]
        });
      };
      // reader가 이미지 읽도록 하기
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  const handleImageClick = (e) => {
    if (totInfo.investContentImg.length < MAX_IMAGES) {
      document.getElementById("imageUpload").click();
    }
  };

  const handleImageDelete = (e, index) => {
    e.stopPropagation();

    const updatedImagesFile = [...totInfo.investContentImg];
    updatedImagesFile.splice(index, 1);

    const updatedImages = [...images];
    updatedImages.splice(index, 1);

    setImages([...updatedImages]);
    setTotInfo({ ...totInfo, investContentImg: [...updatedImages] });
    setShowDeleteIcon(false);
  };

  const handleAddButtonClick = () => {
    setShowDeleteButton(true);
  };

  const navigateToStep1 = useNavigate();
  const navigateToStep2 = useNavigate();

  const handlePreviousStep = () => {
    navigateToStep1("/makeInvestMoneyinfo", { state: { totInfo: totInfo } });
  };

  //   const handleNextStep = () => {
  //     axios
  //       .post("/make-invest/story", totInfo) // 서버의 API 엔드포인트에 맞게 경로와 데이터를 수정해야 합니다.
  //       .then(() => {
  //         navigateToStep2("/make-invest/typelist", {
  //           state: { totInfo: totInfo },
  //         });
  //       })
  //       .catch((error) => {
  //         console.error(error);
  //       });
  //   };

  const handleNextStep = () => {
    navigateToStep2("/makeInvestTypelist", { state: { totInfo: totInfo } });
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
          <div className="proj-progress-ing proj-progress-common proj-progress-line">
            3
          </div>
          <div className="proj-progress proj-progress-common proj-progress-line">
            4
          </div>
          <div className="proj-progress proj-progress-common">5</div>
        </div>

        <p className="custom-font-sub-title">
          <b>프로젝트 소개 영상과 이미지를 등록해주세요</b>
        </p>

        <p className="custom-font-text">
          영상과 이미지를 함께 등록할 경우, 영상이 먼저 보여집니다.
        </p>

        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginTop: "11px" }}
        >
          <b>동영상 주소를 적어주세요</b>
        </p>
        <input
          type="text"
          name="investVideoUrl"
          className="input-box-video"
          onChange={handleInputChange}
          value={totInfo.investVideoUrl}
        />
        {/* <div
          className="projMake-video"
          style={{ flexDirection: "column", alignItems: "flex-start" }}
        >
          {totInfo.investVideoUrl.map((input, index) => (
            <div key={input.id} style={{ marginTop: index > 0 ? "10px" : "0" }}>
              <input
                type="text"
                name="investVideoUrl"
                className="input-box-video"
                onChange={(e) =>
                  (totInfo.investVideoUrl[index].url = e.target.value)
                }
                defaultValue={input.url}
              />
              <button className="rew-add" onClick={handleAddInput}>
                <PlusSquareOutlined style={{ fontSize: "23px" }} />
              </button>
              {index !== 0 && (
                <button
                  className="rew-delete"
                  onClick={() => handleDeleteInput(input.id)}
                >
                  <MinusSquareOutlined style={{ fontSize: "23px" }} />
                </button>
              )}
            </div>
          ))}
        </div> */}
        <br />

        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginBottom: "11px" }}
        >
          <b>이미지를 등록해주세요</b>
        </p>

        <div
          className="imi-image"
          style={{ display: "flex", flexWrap: "wrap" }}
        >
          {images.map((image, index) => (
            <div
              className="imi-image-upload"
              key={index}
              style={{ marginTop: "15px" }}
            >
              <div
                className="imi-image-upload-info"
                onClick={handleImageClick}
                onMouseEnter={() => setShowDeleteIcon(true)}
                onMouseLeave={() => setShowDeleteIcon(false)}
                style={{ cursor: "pointer", width: "130px", height: "85px" }}
              >
                <div
                  className="imi-image-delete"
                  onClick={(e) => handleImageDelete(e, index)}
                  style={{
                    position: "absolute",
                    top: "5px",
                    right: "5px",
                    zIndex: "1",
                    color: "#fff",
                    fontSize: "15px",
                    display: showDeleteIcon ? "block" : "none"
                  }}
                >
                  <MinusCircleOutlined id="imi-image-delete-icon" />
                </div>
                <img src={image.src} alt={image.alt} style={image.style} />
              </div>
            </div>
          ))}
          {images.length < MAX_IMAGES - 1 && (
            <div className="imi-image-upload" style={{ marginTop: "15px" }}>
              <div
                className="imi-image-upload-info"
                onClick={handleImageClick}
                style={{ cursor: "pointer", width: "130px", height: "85px" }}
              >
                <div style={{ marginBottom: "2%" }}>
                  <PlusCircleOutlined
                    style={{
                      fontSize: "15px",
                      cursor: "pointer",
                      marginRight: "3px"
                    }}
                  />
                </div>
                최적 사이즈
                <br />
                740*417px
              </div>
            </div>
          )}
        </div>
        <input
          type="file"
          id="imageUpload"
          accept="image/*"
          style={{ display: "none" }}
          onChange={handleImageUpload}
          onClick={handleImageInit}
        />
        <br />
        <br />
        <br />

        <p className="custom-font-sub-title">
          <b>프로젝트 스토리를 적어주세요</b>
        </p>

        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginTop: "17px" }}
        >
          <b>아이템 소개</b>
        </p>
        <textarea
          type="text"
          name="investItemIntro"
          className="rew-textarea"
          value={totInfo.investItemIntro}
          onChange={handleInputChange}
        />
        <br />
        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginTop: "15px" }}
        >
          <b>아이템의 사업성</b>
        </p>
        <textarea
          type="text"
          name="investItemBusinessValue"
          className="rew-textarea"
          value={totInfo.investItemBusinessValue}
          onChange={handleInputChange}
        />
        <br />
        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginTop: "15px" }}
        >
          <b>아이템의 가치</b>
        </p>
        <textarea
          type="text"
          name="investItemValue"
          className="rew-textarea"
          value={totInfo.investItemValue}
          onChange={handleInputChange}
        />
        <br />
        <p
          className="custom-font-text"
          style={{ fontSize: "12px", marginTop: "15px" }}
        >
          <b>아이템의 기대효과</b>
        </p>
        <textarea
          type="text"
          name="investItemBenefit"
          className="rew-textarea"
          value={totInfo.investItemBenefit}
          onChange={handleInputChange}
        />
        <br />
        <br />
        <br />
        <div className="edit_wrap">
          <Editor
            initialValue={totInfo.investProjContent}
            previewStyle="vertical"
            height="600px"
            hideModeSwitch={true}
            initialEditType="wysiwyg"
            useCommandShortcut={false}
            language="ko-KR"
            ref={editorRef}
            plugins={[colorSyntax]}
            name="investProjContent"
            onChange={handleToastChange}
          />
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
            다음 단계{" "}
          </button>
        </div>
        <div className="button-botoom-margin"></div>
      </div>
      <Footer />
    </>
  );
};
export default MakeInvest3;
