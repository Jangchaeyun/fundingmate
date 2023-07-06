import React from 'react';
import Header from "../../Component/Header/Header";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";
import FindIdForm from "../../Component/Login/FindIdPw/FindIdForm";
import FindPwForm from "../../Component/Login/FindIdPw/FindPwForm";

function FindIdPw(props) {
    return (
        <div className="wrap">
            <Header />
            <FindPwForm />
            <CorFooter />
        </div>
    );
}

export default FindIdPw;