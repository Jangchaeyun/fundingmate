import React, {useState} from "react";
import "./MakeReward5.css";
import "./MakeRewardCommon.css";
import { DatePicker, Space } from 'antd';
import {PlusCircleOutlined,IeOutlined,FacebookOutlined, InstagramOutlined,BoldOutlined ,TwitterOutlined } from "@ant-design/icons";
import  DaumPostcode  from 'react-daum-postcode';
const { RangePicker } = DatePicker;

const MakeReward5 = () => {
    const [selectedImage, setSelectedImage] = useState(null);
    const [selectedImage2, setSelectedImage2] = useState(null);
    const handleImageUpload = (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                setSelectedImage(e.target.result);
            };

            reader.readAsDataURL(file);
        }
    };

    const handleImageClick = () => {
        document.getElementById('imageUpload').click();
    };

    const handleImageUpload2 = (event) => {
        const file = event.target.files[0];

        if (file) {
            const reader = new FileReader();

            reader.onload = (e) => {
                setSelectedImage2(e.target.result);
            };

            reader.readAsDataURL(file);
        }
    };

    const handleImageClick2 = () => {
        document.getElementById('imageUpload2').click();
    };

    const [showPostcode, setShowPostcode] = useState(false);
    const [businessAddress, setBusinessAddress] = useState('');

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = '';

        const { addressType, bname, buildingName } = data;
        if (addressType === 'R') {
            if (bname !== '') {
                extraAddress += bname;
            }
            if (buildingName !== '') {
                extraAddress += `${extraAddress !== '' && ', '}${buildingName}`;
            }
            fullAddress += `${extraAddress !== '' ? ` ${extraAddress}` : ''}`;
        }

        setBusinessAddress(fullAddress);
        setShowPostcode(false);
    };

    const handleAddressSearch = () => {
        setShowPostcode(true);
    };

    return (
        <>
        <div className="investMake-wrapper">
            <div className="proj-progress-div">
                <div className="proj-progress proj-progress-common proj-progress-line">1</div>
                <div className="proj-progress proj-progress-common proj-progress-line">2</div>
                <div className="proj-progress proj-progress-common proj-progress-line">3</div>
                <div className="proj-progress proj-progress-common proj-progress-line">4</div>
                <div className="proj-progress-ing proj-progress-common">5</div>
            </div>

            <p className="custom-font-title">
                <b>진행자 정보를 입력해 주세요</b>
            </p>
            <br/>
            <br/>
            <p className="custom-font-sub-title">
                <b>사업자등록증 이미지를 올려주세요(개인일 경우 신분증 이미지)</b>
            </p>

            <div className="ibi-image-upload">
                <div className="ibi-image-upload-info" onClick={handleImageClick} style={{cursor: 'pointer', width: '35vh', height: '25vh' }}>
                    {selectedImage ? (
                        <img src={selectedImage} alt="Selected" style={{ width: '100%', height: '100%', objectFit: 'cover'}} />
                    ) : (
                        <>
                    <div style={{marginBottom: "2%"}}><PlusCircleOutlined style={{ fontSize: "25px", cursor:"pointer"}}/></div>
                    이미지 추가하기
                        </>
                    )}
                </div>
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

            {/* 주소 찾기 버튼 */}
            <button onClick={handleAddressSearch}>주소 찾기</button>

            {/* DaumPostcode 컴포넌트 */}
            {showPostcode ? (
                <div className="post-code">
                    <DaumPostcode onComplete={handleComplete} />
                </div>
            ) : null}
            <p className="custom-font-sub-title">
                <b>사업자 주소를 선택해 주세요(개인일 경우 신분증 상의 주소)</b>
            </p>
            우편번호  <input type="text" className="input-box" style={{width:"30%"}}/>
            주소 <input
                type="text"
                name="businessAddress"
                className="input-box"
                value={businessAddress}
                readOnly
            />
            상세주소 <input type="text" className="input-box"/>
            <br/>
            <br/>
            <br/>
            <br/>
            <p className="custom-font-title">
                <b>계좌 정보를 입력해 주세요</b>
            </p>

            <p className="custom-font-text">
               프로젝트 종료 후 정산 받을 계좌를 입력해 주세요.
            </p>
            <br/>
            <br/>


            <p className="custom-font-sub-title">
                <b>은행과 계좌 번호를 적어주세요</b>
            </p>

            <select className="bank-name">
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
            <input type="text" name="accNumber" className="bank-num" placeholder="계좌 번호를 숫자만 입력해 주세요.(-제외)"/>
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>예금주명을 적어주세요</b>
            </p>
            <input type="text" name="depositorName" className="input-box" placeholder="계좌에 등록된 예금주명과 일치해야 합니다."/>
            <br/>
            <br/>
            <p className="custom-font-sub-title">
                <b>통장 사본 이미지를 올려주세요</b>
            </p>

            <div className="ibi-image-upload">
                <div className="ibi-image-upload-info"  onClick={handleImageClick2} style={{cursor: 'pointer', width: '35vh', height: '25vh'}}>
                        {selectedImage2 ? (
                                <img src={selectedImage2} alt="Selected" style={{ width: '100%', height: '100%', objectFit: 'cover'}} />
                            ) : (
                                <>
                    <div style={{marginBottom: "2%"}}><PlusCircleOutlined style={{ fontSize: "25px", cursor:"pointer"}}/></div>
                    이미지 추가하기
                                </>
                            )}
                </div>
            </div>
                    <input
                    type="file"
                    id="imageUpload2"
                    accept="image/*"
                    style={{ display: 'none' }}
                     onChange={handleImageUpload2}
                />
            <br/>
            <br/>

            <p className="custom-font-sub-title">
                <b>세금계산서를 발급 받을 이메일을 적어주세요</b>
            </p>

            <input type="text" name="taxBillEmail" className="input-box"/>

            <br/>
            <br/>
            <br/>
            <br/>
            <hr/>
            <br/>
            <br/>
            <br/>

                <p className="custom-font-title">
                    <b>홈페이지와 SNS주소를 적어주세요</b>
                </p>

            <p className="custom-font-text">
               <b style={{color:"#E93232"}}>(선택사항)</b>현재 운영 중인 곳의 주소를 적어주세요. 진행자 정보에 노출됩니다.
            </p>
            <div style={{display:'flex', flexDirection:"column"}}>
                <div className="sns-address-div">
                    <div className="snsAddress"><IeOutlined /></div>
                    <input type="text" name="websiteUrl" className="sns-input-box" placeholder="주소를 입력해주세요."/>
                </div>
                <div className="sns-address-div">
                    <div  className="snsAddress"><FacebookOutlined /></div>
                    <input type="text" name="facebookUrl" className="sns-input-box" placeholder="주소를 입력해주세요."/>
                </div>
                <div className="sns-address-div">
                    <div  className="snsAddress"><InstagramOutlined /></div>
                    <input type="text" name="instagramUrl" className="sns-input-box" placeholder="주소를 입력해주세요."/>
                </div>
                <div className="sns-address-div">
                    <div  className="snsAddress"><BoldOutlined /></div>
                    <input type="text" name="blogUrl" className="sns-input-box" placeholder="주소를 입력해주세요."/>
                </div>
                <div className="sns-address-div">
                    <div  className="snsAddress"><TwitterOutlined /></div>
                    <input type="text" name="twitterUrl" className="sns-input-box" placeholder="주소를 입력해주세요."/>
                </div>

            </div>
            <div className="button-top-margin"></div>
            <div className="investMake-button-div">
                <button className="investMake-prev-button"> 이전 단계 </button>
                <button className="investMake-next-button"> 저장하기 </button>
            </div>
            <div className="button-botoom-margin"></div>

        </div>
        </>
    );
};
export default MakeReward5;
