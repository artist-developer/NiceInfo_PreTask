import React, { useState } from 'react';
import { Redirect } from 'react-router-dom';
import axios , { post } from 'axios';

class LoginForm extends React.Component {

  constructor(props) {
    super(props);
    console.log("props : "+props);
    this.state = {
      result:'',
      id: '',
      password: '',
      redirect: false,
      authToken:""
    }
    this.handleFormSubmit = this.handleFormSubmit.bind(this)
    this.userLogin = this.userLogin.bind(this)
    this.handleValueChange = this.handleValueChange.bind(this)

  }
  //로그인시 페이지 이동시키기 위해 state의 값 변경
  setRedirect = () => {
    this.setState({
      redirect: true
    })
  }
  //로그인시 페이지 이동 시키기 위한 함수
  //authToken에 토큰 값을 저장하여 같이 보낸다. api에서 이를 체크해서 로직을 분기처리한다.
  renderRedirect = () => {
    if (this.state.redirect) {
      return <Redirect to={
        {
         pathname: '/list/'+this.state.authToken,
         state:{ authToken : this.state.authToken }
        }
      }
      
      />
    }
  }

  //form 전송 핸들러로 이곳에서 ajax로 콜하여 페이지이동 및 authToken을 저장한다.
  handleFormSubmit(e) {
    
    e.preventDefault()
     axios({
      method: 'post',
      url: 'http://192.168.99.100:8080/login',
      headers: {}, 
      data: {
        id: this.state.id, 
        password : this.state.password
      }
    })
    .then((response) => {
      console.log(response.data);
      if(null != response){
          if(response.data.result.result_code == 200){
            this.setState({
              authToken: response.data.data.authToken,
              redirect: true
            })
            
          }
      }
    })
    
    
  }

  handleValueChange(e) {
    let nextState = {};
    nextState[e.target.name] = e.target.value;
    this.setState(nextState);
  }

  userLogin(){
  }
  render() {
    return (
      <div>
        <h2>로그인</h2>
        <form onSubmit={this.handleFormSubmit}>
          아이디:<input type="text" name="id" onChange={this.handleValueChange}/><br/>
          비밀번호:<input type="password" name="password" onChange={this.handleValueChange}/><br/>
          <button type="submit">로그인</button>
        </form>
        {this.renderRedirect()}
      </div>
    );
  }
}

export default LoginForm;