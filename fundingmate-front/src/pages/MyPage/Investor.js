import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import './MyPage.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function Investor() {
    return (
        <>
            {/* header */}
            <div className="mypage-container">
                <h1>마이페이지</h1>
            </div>
            <Navbar className="subheader">
                <Container>
                    <Nav>
                        <Nav.Link href="#my-settings">설정</Nav.Link>
                        <Nav.Link href="#payment">결제</Nav.Link>
                        <Nav.Link href="#my-projects">제작한 프로젝트</Nav.Link>
                        <Nav.Link href="#other-projects">참여한 프로젝트</Nav.Link>
                        <Nav.Link href="#following">관심 프로젝트</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <div className="section-container" id="my-settings">
                <h4>설정</h4>
            </div>
            <table>
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
            <div className="section-container" id="my-settings">
                <h4>투자 정보</h4>
            </div>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <Form.Group>
                                <Form.Label>사업자번호: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <Form.Control type="text" />
                            </Form.Group>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Form.Group>
                                <Form.Label>기업명: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <Form.Control type="text" />&nbsp;&nbsp;
                            </Form.Group>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Form.Group>
                                <Form.Label>대표자: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <Form.Control type="text" />&nbsp;&nbsp;
                            </Form.Group>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Form.Group>
                                <Form.Label>업종: </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <Form.Control type="text" />&nbsp;&nbsp;
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
                            </Form.Group>
                        </td>
                    </tr>
                </tbody>
            </table >
            <div className="section-container" id="payment">
                <h4>결제</h4>
            </div>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <Form.Group>
                                <Form.Label> </Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <Form.Control type="text" />
                            </Form.Group>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div className="section-container" id="my-projects">
                <h4>제작한 프로젝트</h4>
            </div>
            <div className="section-subheader">
                <h4>리워드 펀딩</h4>
            </div>
            <div className="subsection-container">
                <table className="list">
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
                <table className="list">
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
            <div className="section-container" id="other-projects">
                <h4>참여한 프로젝트</h4>
            </div>
            <div className="section-subheader">
                <h4>리워드 펀딩</h4>
            </div>
            <div className="subsection-container">
                <table className="list">
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
                <table className="list">
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
            <div className="section-container" id="following">
                <h4>관심 프로젝트</h4>
            </div>
            <div className="section-subheader">
                <h4>리워드 펀딩</h4>
            </div>
            <div className="subsection-container">
                <table className="list">
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
                <table className="list">
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
            <div className="section-container" id="following">
                <h4>기타</h4>
            </div>
            <table style={{ textIndent: 0 }}>
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
        </>
    );
}

export default Investor;