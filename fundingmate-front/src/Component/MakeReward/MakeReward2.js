import React, { useRef, useState } from 'react';
import { Route, useNavigate } from "react-router-dom";
import "./MakeReward2.css";
import "./MakeRewardCommon.css";

import {PlusCircleOutlined, PlusSquareOutlined, MinusCircleOutlined, MinusSquareOutlined} from "@ant-design/icons";


import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import '@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css';
import '@toast-ui/editor/dist/i18n/ko-kr';
const MAX_IMAGES = 15;

const MakeReward2 = () => {
    const editorRef = useRef();
    const onChange = () => {
        const data = editorRef.current.getInstance().getHTML();
        console.log(data);}

    const [inputs, setInputs] = useState([{ id: 1 }]);

    const handleAddInput = () => {
        const newId = inputs.length + 1;
        const newInput = { id: newId };
        setInputs((prevInputs) => [...prevInputs, newInput]);
    };

    const handleDeleteInput = (id) => {
        setInputs((prevInputs) => prevInputs.filter((input) => input.id !== id));
    };
    const [showDeleteIcon, setShowDeleteIcon] = useState(false);
    const [images, setImages] = useState([]);
    const [showDeleteButton, setShowDeleteButton] = useState(false);
    const handleImageUpload = (event) => {
        const files = event.target.files;
        const newImages = [];

        const uploadImage = (file) => {
            return new Promise((resolve, reject) => {
                const reader = new FileReader();

                reader.onload = (e) => {
                    const image = {
                        src: e.target.result,
                        alt: 'Selected',
                        style: { width: '100%', height: '100%', objectFit: 'cover' }
                    };
                    resolve(image);
                };

                reader.onerror = (error) => {
                    reject(error);
                };

                reader.readAsDataURL(file);
            });
        };

        const uploadAllImages = async () => {
            for (const file of files) {
                try {
                    const image = await uploadImage(file);
                    newImages.push(image);
                } catch (error) {
                    console.error('Error uploading image:', error);
                }
            }
            setImages((prevImages) => [...prevImages, ...newImages]);
        };

        uploadAllImages();
    };

    const handleImageClick = () => {
        if (images.length < MAX_IMAGES) { // Replace MAX_IMAGES with the maximum number of images allowed
            document.getElementById('imageUpload').click();
        }
    };

    const handleImageDelete = (index) => {
        setImages((prevImages) => {
            const updatedImages = [...prevImages];
            updatedImages.splice(index, 1);
            return updatedImages;
        });
        setShowDeleteIcon(false);
    };

    const handleAddButtonClick = () => {
        setShowDeleteButton(true);
    };

    const navigateToStep1 = useNavigate();
    const navigateToStep2 = useNavigate();

    const handlePreviousStep = () => {
        navigateToStep1("/make-reward/basicinfo");
    };

    const handleNextStep = () => {
        navigateToStep2("/make-reward/typelist");
    };


    return (
        <>
        <div className="investMake-wrapper">

            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress-ing proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress proj-progress-common">5</div>
            </div>


            <p className="custom-font-sub-title">
                <b>프로젝트 소개 영상과 이미지를 등록해주세요</b>
            </p>

            <p className="custom-font-text">
                영상과 이미지를 함께 등록할 경우, 영상이 먼저 보여집니다.
            </p>

            <p className="custom-font-text" style={{fontSize:"12px", marginTop:"11px"}}>
                <b>동영상 주소를 적어주세요</b>
            </p>
            <div className="projMake-video" style={{flexDirection:"column", alignItems: "flex-start"}}>
                {inputs.map((input, index) => (
                    <div key={input.id} style={{ marginTop: index > 0 ? '10px' : '0' }}>
                        <input type="text" name="rewardVideoAddress" className="input-box-video"/>
                        <button className="rew-add" onClick={handleAddInput}>
                            <PlusSquareOutlined style={{ fontSize: "23px" }} />
                        </button>
                        {input.id !== 1 && (
                            <button className="rew-delete" onClick={() => handleDeleteInput(input.id)}>
                                <MinusSquareOutlined style={{ fontSize: "23px" }} />
                            </button>
                        )}
                    </div>
                ))}
            </div>
            <br />

            <p className="custom-font-text" style={{fontSize:"12px", marginBottom:"11px"}}>
                <b>이미지를 등록해주세요</b>
            </p>

            <div className="imi-image" style={{ display: 'flex', flexWrap: 'wrap' }}>
                {images.map((image, index) => (
                    <div className="imi-image-upload" key={index} style={{ marginTop: '15px' }}>
                        <div
                            className="imi-image-upload-info"
                            onClick={handleImageClick}
                            onMouseEnter={() => setShowDeleteIcon(true)}
                            onMouseLeave={() => setShowDeleteIcon(false)}
                            style={{ cursor: 'pointer', width: '130px', height: '85px' }}
                        >
                            <div
                                className="imi-image-delete"
                                onClick={() => handleImageDelete(index)}
                                style={{ position: 'absolute', top: '5px', right: '5px', zIndex: '1', color: '#fff', fontSize: '15px' , display: showDeleteIcon ? 'block' : 'none' }}
                            >
                                <MinusCircleOutlined id="imi-image-delete-icon"/>
                            </div>
                            <img src={image.src} alt={image.alt} style={image.style} />
                        </div>
                    </div>
                ))}
                {images.length < MAX_IMAGES - 1 && (
                <div className="imi-image-upload" style={{ marginTop: '15px' }}>
                    <div
                        className="imi-image-upload-info"
                        onClick={handleImageClick}
                        style={{ cursor: 'pointer', width: '130px', height: '85px' }}
                    >
                        <div style={{ marginBottom: '2%' }}>
                            <PlusCircleOutlined style={{ fontSize: '15px', cursor: 'pointer', marginRight: '3px' }} />
                        </div>
                        최적 사이즈<br />740*417px
                    </div>
                </div>
                )}
            </div>
            <input
                type="file"
                id="imageUpload"
                accept="image/*"
                style={{ display: 'none' }}
                onChange={handleImageUpload}
            />
            <br/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>프로젝트 스토리를 적어주세요</b>
            </p>

            <div className="edit_wrap">
                <Editor
                    initialValue=""
                    previewStyle="vertical"
                    height="600px"
                    hideModeSwitch={true}
                    initialEditType="wysiwyg"
                    useCommandShortcut={false}
                    language="ko-KR"
                    ref={editorRef}
                    plugins={[colorSyntax]}
                />
            </div>


            <div className="button-top-margin"></div>
            <div className="investMake-button-div">
            <button className="investMake-prev-button" onClick={handlePreviousStep}> 이전 단계 </button>
            <button className="investMake-next-button" onClick={handleNextStep}> 다음 단계 </button>
            </div>
                <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward2;
