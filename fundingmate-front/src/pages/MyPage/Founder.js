import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import './MyPage.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function Founder() {
    return (
        <>
            {/* header */}
            <div className="mypage-container">
                <h1>마이페이지</h1>
            </div>
            <Navbar className="subheader">
                <Container>
                    <Nav>
                        <Nav.Link href="#">설정</Nav.Link>
                        <Nav.Link href="#">간편 결제</Nav.Link>
                        <Nav.Link href="#">제작한 프로젝트</Nav.Link>
                        <Nav.Link href="#">참여한 프로젝트</Nav.Link>
                        <Nav.Link href="#">관심 프로젝트</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            <div className="section-container">
                <h4>설정</h4>
            </div>
            <div className="settings">
                <h3>기본 정보</h3>
            </div>
            <Form className="form-container">
                <Form.Group>
                    <Form.Label>이름: </Form.Label>
                    <Form.Control type="text" />
                </Form.Group>
                <Form.Group>
                    <Form.Label>이메일: </Form.Label>
                    <Form.Control type="email" />
                </Form.Group>
                {/* <Button variant="primary" type="submit">
           Click here to submit form
        </Button> */}
            </Form>
        </>
    );
}

export default Founder;