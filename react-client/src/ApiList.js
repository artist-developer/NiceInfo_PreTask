import React, {Component} from 'react';
import axios from "axios"

class ApiList extends Component{

  //API 호출에 필요한 데이터 정리
  constructor(props){
    super(props);
    this.state = {
      contents:[
        
      ],
      createDay:"",
      id:"",
      password:"",
      redirect: null
    }
  }
  //const query = queryString.parse(location.search);
  //console.log(query.authToken);
  //랜더링 후 API호출
  componentDidMount(){
   
    console.log();
    
    if(null != this.props.location.state.authToken){
      axios.get("http://192.168.99.100:8080/apiList").then(res => {
        this.setState({
          contents : res.data.data,
          createDay : res.data.data[0].createDay,
          redirect : true
        });
      })
    }    
  }
  

  //랜더링
  render(){
  	var lists = [];
  	//state에 담긴 리스트들을 와일문으로 풀어서 리스트에 담는다.

    //createDay 는 createDay 컬럼으로 가져옵니다.
    //리스트의 값이 0이상일시에는 담긴 내용을 노출시키고 그게 아닐시에는 기존 내용을 노출시킨다
    var data = this.state.contents;
    var createDay = this.state.createDay;
  	var i = 0;
  	while(i < data.length){
  		lists.push(
        <tr key={data[i].seq}>
          <td>{data[i].rnum}</td>
          <td>{data[i].platPlc}</td>
          <td>{data[i].sigunguCd}</td>
          <td>{data[i].bjdongCd}</td>
          <td>{data[i].platGbCd}</td>
          <td>{data[i].bun}</td>
          <td>{data[i].ji}</td>
          <td>{data[i].mgmBldrgstPk}</td>
          <td>{data[i].mgmUpBldrgstPk}</td>
          <td>{data[i].regstrGbCd}</td>
          <td>{data[i].regstrGbCdNm}</td>
          <td>{data[i].regstrKindCd}</td>
          <td>{data[i].regstrKindCdNm}</td>
          <td>{data[i].newPlatPlc}</td>
          <td>{data[i].bldNm}</td>
          <td>{data[i].splotNm}</td>
          <td>{data[i].block}</td>
          <td>{data[i].lot}</td>
          <td>{data[i].bylotCnt}</td>
          <td>{data[i].naRoadCd}</td>
          <td>{data[i].naBjdongCd}</td>
          <td>{data[i].naUgrndCd}</td>
          <td>{data[i].naMainBun}</td>
          <td>{data[i].naSubBun}</td>
          <td>{data[i].jiyukCd}</td>
          <td>{data[i].jiguCd}</td>
          <td>{data[i].guyukCd}</td>
          <td>{data[i].jiyukCdNm}</td>
          <td>{data[i].jiguCdNm}</td>
          <td>{data[i].guyukCdNm}</td>
          <td>{data[i].crtnDay}</td>
        </tr>
      );
  		i = i + 1;
  	}
    return(
    <div>
      
      <h3>생성 일자 : {createDay}</h3>
      <table class="table table-bordered">
        <thead>
          <tr>
             <th>#</th>
             <th>대지위치</th>
             <th>시군구코드</th>
             <th>법정동코드</th>
             <th>대지구분코드</th>
             <th>번</th>
             <th>지</th>
             <th>관리건축물대장(PK)</th>
             <th>관리상위건축물대장(PK)</th>
             <th>대장구분코드</th>
             <th>대장구분코드명</th>
             <th>대장종류코드</th>
             <th>대장종류코드명</th>
             <th>도로명대지위치</th>
             <th>건물명</th>
             <th>특수지명</th>
             <th>블록</th>
             <th>로트</th>
             <th>외필지수</th>
             <th>새주소도로코드</th>
             <th>새주소법정동코드</th>
             <th>새주소지상지하코드</th>
             <th>새주소본번</th>
             <th>새주소부번</th>
             <th>지역코드</th>
             <th>지구코드</th>
             <th>구역코드</th>
             <th>지역코드명</th>
             <th>지구코드명</th>
             <th>구역코드명</th>
             <th>생성일자</th>
          </tr>
        </thead>
        <tbody>
          
          {lists.length > 0 ? lists : <tr><td colspan="31"><h1>데이터를 가져오는 중입니다.</h1></td></tr>}
        </tbody>
        
      </table>
    </div>
    );
  }
}

export default ApiList;