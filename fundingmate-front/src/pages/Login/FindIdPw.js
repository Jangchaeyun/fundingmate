import React from 'react';
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdPwForm from "../../Component/Login/FindIdPwForm";

function FindIdPw(props) {
    return (
        <div className="findIdPw">
            <Header />
            <FindIdPwForm />
            <CorFooter />
        </div>
    );
}

export default FindIdPw;