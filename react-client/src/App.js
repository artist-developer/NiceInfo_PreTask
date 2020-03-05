import React, {Component} from 'react';
import { BrowserRouter as Router, Route, Link , Switch } from 'react-router-dom';


import Login from './components/Login';
import ApiList from './components/ApiList';
import axios from "axios"

import './App.css';


class App extends Component{
  constructor(props){
    super(props);
    this.state = {
      header:{title : ""},
      contents:[
        
      ],
      createDay:""
    }
  }
  

  render(){
    return(
      <Router>
        <div>
          <nav>
            <ul>
              <li>
                <Link to="/">Login</Link>
              </li>
              <li>
                <Link to="/list">ApiList</Link>
              </li>
            </ul>
          </nav>

          <Route exact path='/' component={Login}/>
          <Route path='/list' component={ApiList}/>
        </div>
      </Router>
      
    );
  }

}

export default App;