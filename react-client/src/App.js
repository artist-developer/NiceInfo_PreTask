import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Link ,Switch } from 'react-router-dom';

//권한 처리
import AuthRoute from './AuthRoute';
//로그인 라우터
import LoginForm from './LoginForm';
//API 라우터
import ApiList from './ApiList';
//API Call을 위한 라이브러리
import axios from "axios"

import './App.css';

//최고 상위 콤포넌트
//authToken를 자식에게서 받아 권환 처리
class App extends Component{
  constructor(props){
    console.log("props : "+props);
    super(props);
    this.state = {
      header:{title : ""},
      contents:[
        
      ],
      createDay:"",
      
    }
  }

  //라우터로 양 컴포넌트 병합
  render(){
    return(
      <Router>
        <div>
          <Route exact path='/' component={LoginForm}/>
          <Route path='/list/:authToken' component={ApiList}/>
        </div>
      </Router>
      
    );
  }

}

export default App;
