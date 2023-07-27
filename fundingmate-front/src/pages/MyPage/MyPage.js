import React, { useEffect, useState } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import './MyPage.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { notification } from 'antd';
import Header from '../../Component/Header/Header';
import Footer from '../../Component/Footer/Footer';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

// Define the API_BASE_URL directly in MyPage.js
const API_BASE_URL = 'http://localhost:8080';

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

    const [userInfo, setUserInfo] = useState({});

    useEffect(() => {
        // Make the API request to fetch user information for "My Page"
        axios.get(`${API_BASE_URL}/myPage`)
            .then(response => setUserInfo(response.data))
            .catch(error => console.error('Error fetching my settings info:', error));
    }, []);

    // Make additional API calls for other sections here
    useEffect(() => {
        if (activeSection === 'business-license') {
            // Make the API request to fetch business license information
            // Replace the below endpoint with your actual backend API endpoint for business license data
            axios.get(`${API_BASE_URL}/myPageBusinessLicense`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setBusinessLicenseInfo(response.data)
                })
                .catch(error => console.error('Error fetching business license info:', error));
        } else if (activeSection === 'my-projects') {
            // Make the API request to fetch user's projects information
            // Replace the below endpoint with your actual backend API endpoint for user's projects data
            axios.get(`${API_BASE_URL}/myPageMyProjects`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setMyProjectsInfo(response.data)
                })
                .catch(error => console.error('Error fetching my projects info:', error));
        } else if (activeSection === 'other-projects') {
            // Make the API request to fetch user's projects information
            // Replace the below endpoint with your actual backend API endpoint for user's projects data
            axios.get(`${API_BASE_URL}/myPageOtherProjects`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setMyProjectsInfo(response.data)
                })
                .catch(error => console.error('Error fetching other projects info:', error));
        } else if (activeSection === 'following') {
            // Make the API request to fetch user's projects information
            // Replace the below endpoint with your actual backend API endpoint for user's projects data
            axios.get(`${API_BASE_URL}/myPageFollowing`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setMyProjectsInfo(response.data)
                })
                .catch(error => console.error('Error fetching following info:', error));
        } else if (activeSection === 'other') {
            // Make the API request to fetch user's projects information
            // Replace the below endpoint with your actual backend API endpoint for user's projects data
            axios.get(`${API_BASE_URL}/myPageOther`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setMyProjectsInfo(response.data)
                })
                .catch(error => console.error('Error fetching other info:', error));
        } else {
            // Make the API request to fetch user's projects information
            // Replace the below endpoint with your actual backend API endpoint for user's projects data
            axios.get(`${API_BASE_URL}/myPage`)
                .then(response => {
                    // Handle the response data accordingly, e.g., setMyProjectsInfo(response.data)
                })
                .catch(error => console.error('Error fetching my settings info:', error));
        }
        // Add more conditions for other sections if needed
    }, [activeSection]);

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

    // State variables to hold updated email, tel, and password values
    const [updatedEmail, setUpdatedEmail] = useState('');
    const [updatedTel, setUpdatedTel] = useState('');
    const [updatedPassword, setUpdatedPassword] = useState('');

    const handleEmailSubmit = () => {
        // Make the API request to update the email
        axios.put(`${API_BASE_URL}/updateEmail`, { email: updatedEmail })
            .then(response => {
                // Handle success
                // For example, show a success notification or update the user info in state
                notification.success({ message: 'Email updated successfully.' });
                setUserInfo({ ...userInfo, email: updatedEmail });
                setUpdatedEmail('');
            })
            .catch(error => {
                // Handle error
                // For example, show an error notification
                notification.error({ message: 'Failed to update email. Please try again.' });
            });
    };

    const handleTelSubmit = () => {
        // Make the API request to update the tel
        axios.put(`${API_BASE_URL}/updateTel`, { tel: updatedTel })
            .then(response => {
                // Handle success
                // For example, show a success notification or update the user info in state
                notification.success({ message: 'Tel updated successfully.' });
                setUserInfo({ ...userInfo, tel: updatedTel });
                setUpdatedTel('');
            })
            .catch(error => {
                // Handle error
                // For example, show an error notification
                notification.error({ message: 'Failed to update tel. Please try again.' });
            });
    };

    const handleEmailUpdate = (newEmail) => {
        // Make the API request to update the email
        const requestData = {
            id: userInfo.id,
            email: newEmail,
        };

        axios.put(`${API_BASE_URL}/updateEmail`, requestData)
            .then(response => {
                // Email updated successfully, you may want to show a success message to the user
                console.log('Email updated successfully:', response.data);
            })
            .catch(error => {
                // Handle error here, show error message to the user, etc.
                console.error('Error updating email:', error);
            });
    };

    const handleTelUpdate = (newTel) => {
        // Make the API request to update the telephone number
        const requestData = {
            id: userInfo.id,
            tel: newTel,
        };

        axios.put(`${API_BASE_URL}/updateTel`, requestData)
            .then(response => {
                // Telephone number updated successfully, you may want to show a success message to the user
                console.log('Telephone number updated successfully:', response.data);
            })
            .catch(error => {
                // Handle error here, show error message to the user, etc.
                console.error('Error updating telephone number:', error);
            });
    };

    const handlePasswordUpdate = (newPassword) => {
        // Make the API request to update the password
        const requestData = {
            id: userInfo.id,
            password: newPassword,
        };

        axios.put(`${API_BASE_URL}/updatePassword`, requestData)
            .then(response => {
                // Password updated successfully, you may want to show a success message to the user
                console.log('Password updated successfully:', response.data);
            })
            .catch(error => {
                // Handle error here, show error message to the user, etc.
                console.error('Error updating password:', error);
            });
    };

    const [confirmedPassword, setConfirmedPassword] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);

    const handlePasswordSubmit = (event) => {
        event.preventDefault();

        // Check if the updated password and confirmed password match
        if (updatedPassword !== confirmedPassword) {
            setPasswordsMatch(false);
            return;
        }

        // Make the API request to update the password if the passwords match
        const requestData = {
            id: userInfo.id,
            password: updatedPassword,
        };

        axios.put(`${API_BASE_URL}/updatePassword`, requestData)
            .then(response => {
                // Password updated successfully, you may want to show a success message to the user
                console.log('Password updated successfully:', response.data);
                // Clear the input fields and reset the passwordsMatch state
                setUpdatedPassword('');
                setConfirmedPassword('');
                setPasswordsMatch(true);
            })
            .catch(error => {
                // Handle error here, show error message to the user, etc.
                console.error('Error updating password:', error);
            });
    };

    const history = useHistory();

    const handleDeleteAccount = () => {
        // Assuming you have a function in your API to delete the user account
        axios.delete(`${API_BASE_URL}/deleteAccount/${userId}`)
            .then(() => {
                // Account deleted successfully, navigate back to the main page
                history.push('/'); // Replace '/' with the route of your main page
            })
            .catch(error => {
                console.error('Error deleting account:', error);
                // Handle error if needed
            });
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
                                        <Form.Label>이름: {userInfo.name}</Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <Form.Control type="text" />
                                    </Form.Group>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <Form.Group>
                                        <Form.Label>이메일: {userInfo.email}</Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <Form.Control type="email" value={updatedEmail} onChange={(e) => setUpdatedEmail(e.target.value)} />&nbsp;&nbsp;
                                        <Button type="submit" onClick={handleEmailSubmit}>확인</Button>
                                    </Form.Group>
                                </td>
                            </tr>
                            {/*<tr>*/}
                            {/*    <td>*/}
                            {/*        <Form.Group >*/}
                            {/*            <Form.Label>주소: {userInfo.address}</Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*/}
                            {/*            <Form.Control type="address" />&nbsp;&nbsp;*/}
                            {/*            <Button type="submit">검색</Button>*/}
                            {/*        </Form.Group>*/}
                            {/*    </td>*/}
                            {/*</tr>*/}
                            <tr>
                                <td>
                                    <Form.Group >
                                        <Form.Label>연락처: {userInfo.tel}</Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <Form.Control type="phone" value={updatedTel} onChange={(e) => setUpdatedTel(e.target.value)} />&nbsp;&nbsp;
                                        <Button type="submit" onClick={handleTelSubmit}>인증</Button>
                                    </Form.Group>
                                </td>
                            </tr>
                            <tr>
                                <td><br></br></td>
                            </tr>
                            <tr>
                                <td>
                                    <Form.Group>
                                        <Form.Label>비밀번호: {userInfo.password}</Form.Label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <Form.Control type="password" value={updatedPassword} onChange={(e) => setUpdatedPassword(e.target.value)} />
                                    </Form.Group>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <Form.Group>
                                        <Form.Label>비밀번호 확인: {userInfo.password}</Form.Label>&nbsp;
                                        <Form.Control type="password" value={confirmedPassword} onChange={(e) => setConfirmedPassword(e.target.value)} />&nbsp;&nbsp;
                                        {!passwordsMatch && <span style={{ color: 'red' }}>Passwords do not match</span>}
                                        <Button type="submit" onClick={handlePasswordSubmit}>변경</Button>
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
                                        <Button type="button" onClick={handleDeleteAccount}>탈퇴</Button>
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