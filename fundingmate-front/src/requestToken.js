import axios from 'axios';

export const reqToken = async(token,dispatch,cookie,setCookie,url) => {
    console.log("refreshToken"+cookie.refreshToken);
    try {
        const res = await axios.post("http://localhost:8090/loginCheck", null,
            {
                headers: {
                    Authorization: token+','+cookie.refreshToken
                },
            })
    } catch(err) {
        if(err.request.status==401) {
            console.log(err.response.data)
            const rescode = err.response.data.rescode;
            console.log(rescode)
            if(rescode==101) { //refreshToken 유효, 두개의 토큰 재발급됨.
                //accssToken redux에 저장
                dispatch({type:"NEWTOKEN",data:err.response.data.accessToken})
                //refreshToken cookie에 저장
                const expires = new Date();
                expires.setDate(expires.getDate()+1);
                setCookie('refreshToken',err.response.data.refreshToken, {
                    url:'/', expires
                })
                console.log("url:"+url)
                document.location.href=url;
            } else if(rescode==102) {
                dispatch({type:"NEWTOKEN",payload:''})
                dispatch({type:"USERID",payload:''})
                document.location.href="/";
            }
        }
    }
}