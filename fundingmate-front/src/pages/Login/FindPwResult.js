import React from 'react';
import Header from "../../Component/Header/Header";
import FindIdPwForm from "../../Component/Login/FindIdPw/FindIdPwForm";
import CorFooter from "../../Component/Footer/CorFooter";
import FindIdResultForm from "../../Component/Login/FindIdPw/FindIdResultForm";
import FindPwResultForm from "../../Component/Login/FindIdPw/FindPwResultForm";

function FindIdResult(props) {
    return (
        <div className="wrap">
            <Header />
            <FindPwResultForm />
            <CorFooter />
        </div>
    );
}

export default FindIdResult;