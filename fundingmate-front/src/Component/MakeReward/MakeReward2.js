import React, {useRef} from "react";
import "./MakeReward2.css";
import "./MakeRewardCommon.css";


import {PlusCircleOutlined, PlusSquareOutlined} from "@ant-design/icons";

import { Editor } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor.css';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import 'tui-color-picker/dist/tui-color-picker.css';
import '@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css';
import '@toast-ui/editor/dist/i18n/ko-kr';


const MakeReward2 = () => {
    const editorRef = useRef();
    const onChange = () => {
        const data = editorRef.current.getInstance().getHTML();
        console.log(data);}

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
            <div className="projMake-video">
            <input type="text" name="rew_videp_address" className="input-box"/>
            <button className="video-add"><PlusSquareOutlined style={{ fontSize: "23px"}} /></button>
            </div>
            <br/>

            <p className="custom-font-text" style={{fontSize:"12px", marginBottom:"11px"}}>
                <b>이미지를 등록해주세요</b>
            </p>
            <div className="imi-image">
            <div className="imi-image-upload">
                <div className="imi-image-upload-info">
                    <div style={{marginBottom: "2%"}}><PlusCircleOutlined style={{ fontSize: "15px", cursor:"pointer" , marginRight:"3px"}}/></div>
                    최적 사이즈<br/> 740*417px
                </div>
            </div>
            <div className="imi-image-upload">
                <div className="imi-image-upload-info">
                    <div><PlusCircleOutlined style={{ fontSize: "19px", cursor:"pointer" , marginRight:"3px"}}/></div>

                </div>
            </div>
            </div>

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
            <button className="investMake-prev-button"> 이전 단계 </button>
            <button className="investMake-next-button"> 다음 단계 </button>
            </div>
                <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward2;
