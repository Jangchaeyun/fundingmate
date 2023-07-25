import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import './MyPage.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { notification } from 'antd';
import Header from '../../Component/Header/Header';
import Footer from '../../Component/Footer/Footer';

function MyPage() {
    const [activeSection, setActiveSection] = useState('my-settings');
    const [hasNotifications, setHasNotifications] = useState(false);
    const handleNotificationImageClick = () => {
        if (hasNotifications) {
            //   api.open({
            //     message: '',
            //   });
        } else {
            notification.info({
                message: '알림이 없습니다.'
            });
        }
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();

        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];

        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            // Send the formData to the server or perform any other required actions
            console.log(formData);
        }
    };

    return (
        <div className='mypage-body'>
            <Header />
            <div className="mypage-container">
                <h1>마이페이지</h1>
                <img
                    src={hasNotifications ? "/assets/imgs/notifications_new.png" : "/assets/imgs/notifications_default.png"}
                    className="notifications"
                    alt="notifications"
                    onClick={handleNotificationImageClick}
                />
            </div>
            <Navbar className="subheader">
                <Container>
                    <Nav>
                        <Nav.Link href="#my-settings" onClick={() => setActiveSection('my-settings')}>설정</Nav.Link>
                        <Nav.Link href="#business-license" onClick={() => setActiveSection('business-license')}>투자 정보</Nav.Link>
                        <Nav.Link href="#my-projects" onClick={() => setActiveSection('my-projects')}>제작한 프로젝트</Nav.Link>
                        <Nav.Link href="#other-projects" onClick={() => setActiveSection('other-projects')}>참여한 프로젝트</Nav.Link>
                        <Nav.Link href="#following" onClick={() => setActiveSection('following')}>관심 프로젝트</Nav.Link>
                        <Nav.Link href="#other" onClick={() => setActiveSection('other')}>기타</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>

            {activeSection === 'my-settings' && (
                <div id="my-settings">
                    <div className="section-container">
                        <h4>설정</h4>
                    </div>
                    <table className="my-settings mypage-table">
                        <thead>
                        <tr>
                            <th>
                                <h3>기본 정보</h3>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <img src="/assets/imgs/user.png" className="profile-image" alt="Profile" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group>
                                    <Form.Label>이름: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Form.Control type="text" />
                                </Form.Group>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group>
                                    <Form.Label>이메일: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Form.Control type="email" />&nbsp;&nbsp;
                                    <Button type="submit">확인</Button>
                                </Form.Group>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group >
                                    <Form.Label>주소: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Form.Control type="address" />&nbsp;&nbsp;
                                    <Button type="submit">검색</Button>
                                </Form.Group>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group >
                                    <Form.Label>연락처: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Form.Control type="phone" />&nbsp;&nbsp;
                                    <Button type="submit" >인증</Button>
                                </Form.Group>
                            </td>
                        </tr>
                        <tr>
                            <td><br></br></td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group>
                                    <Form.Label>비밀번호: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Form.Control type="password" />
                                </Form.Group>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <Form.Group>
                                    <Form.Label>비밀번호 확인: </Form.Label>&nbsp;
                                    <Form.Control type="password" />&nbsp;&nbsp;
                                    <Button type="submit">변경</Button>
                                </Form.Group>
                            </td>
                        </tr>
                        </tbody>
                    </table >
                </div>
            )}

            {activeSection === 'business-license' && (
                <div id="business-license">
                    <div className="section-container">
                        <h4>투자 정보</h4>
                    </div>
                    <Form onSubmit={handleFormSubmit}>
                        <table className='mypage-table'>
                            <tbody>
                            <tr>
                                <td>
                                    <Form.Group style={{ textAlign: 'center' }}>
                                        <Form.Label>사업자/주민등록증 업로드: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <Form.Control type="file" accept=".jpg, .png, .pdf, .gif, .tif, .bmp" />&nbsp;&nbsp;
                                        <Button type="submit">확인</Button>
                                    </Form.Group>
                                </td>
                            </tr>
                            </tbody>
                        </table >
                    </Form>
                </div>
            )}

            {activeSection === 'my-projects' && (
                <div id="my-projects">
                    <div className="section-container" id="my-projects">
                        <h4>제작한 프로젝트</h4>
                    </div>
                    <div className="section-subheader">
                        <h4>리워드 펀딩</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                    <div className="section-subheader">
                        <h4>투자</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div >
            )}

            {activeSection === 'other-projects' && (
                <div id="other-projects">
                    <div className="section-container" id="other-projects">
                        <h4>참여한 프로젝트</h4>
                    </div>
                    <div className="section-subheader">
                        <h4>리워드 펀딩</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                    <div className="section-subheader">
                        <h4>투자</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div >
            )}

            {activeSection === 'following' && (
                <div id="following">
                    <div className="section-container" id="following">
                        <h4>관심 프로젝트</h4>
                    </div>
                    <div className="section-subheader">
                        <h4>리워드 펀딩</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                    <div className="section-subheader">
                        <h4>투자</h4>
                    </div>
                    <div className="subsection-container">
                        <table className="list mypage-table" style={{ textAlign: 'center' }}>
                            <tr>
                                <td>#</td>
                                <td>프로젝트</td>
                                <td>금액</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div >
            )}

            {activeSection === 'other' && (
                <div id="other">
                    <div className="section-container" id="other">
                        <h4>기타</h4>
                    </div>
                    <table className='mypage-table' style={{ textIndent: 0 }}>
                        <tbody>
                        <tr>
                            <td>
                                <Form.Group style={{ textAlign: 'center' }}>
                                    <Form.Label><b>회원탈퇴</b></Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <Button type="submit">탈퇴</Button>
                                </Form.Group>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div >
            )}
            <Footer />
        </div>
    );
}

export default MyPage;