import './App.css';
import Header from './components/Header';
import Dropdown from './components/Dropdown';
import SmallContainer from './components/SmallContainer';
import {useState,useEffect} from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import DatePicker from "react-datepicker";
import {ReactUTCDatepicker} from 'react-utc-datepicker';
import Button from 'react-bootstrap/Button'
import "react-datepicker/dist/react-datepicker.css";
import moment from 'moment';

function App() {

  const [value, setValue] = useState(null);
  const [options,setOptions] = useState([]);
  const [data,setData] = useState("No Data");
  const [historyData,setHistoryData] = useState("No History Data");
  const [isHistory,setIsHistory] = useState(false);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const [days,setDays] = useState(1);

  const fetchStatistics = (value) => {
    var lowerValue = value.toLowerCase();
    return fetch(`http://localhost:8080/api/statistics/${lowerValue}`)
    .then((response => response.json()))
  };

  const getStatistics = (value) => {
    return (fetchStatistics(value))
  }

  const getHistory = (value,startDate,endDate) => {
    fetchHistory(value,startDate,endDate).then((dataReceived) => {
      let tmp = JSON.stringify(dataReceived)
      console.log("HISTORICO MAS NO FETCJ")
      console.log(tmp)
      setHistoryData(tmp)
      setIsHistory(true)
      var end = moment(endDate)
      var start = moment(startDate)
      var duration = moment.duration(end.diff(start));
      var days = duration.asDays();
      setDays(days)
    })
    
  }



  const fetchHistory = (value,startDate,endDate) => {
    if(startDate!==null && endDate!==null && value!==null) {
      var lowerValue = value.toLowerCase();
      console.log(startDate.toString())
      return fetch(`http://localhost:8080/api/history/${lowerValue}/${startDate.toString()}?date2=${endDate.toString()}`)
      .then((response => response.json()))
    }
  }

  const buttonChange = (event) => {
    setValue(event.target.value);
    getStatistics(event.target.value).then((dataReceived) => {
      console.log(dataReceived)
      let tmp = JSON.stringify(dataReceived)
      setData(tmp)
      setIsHistory(false)
    })
  };


  //Fetch the countries
  useEffect(() => {
    fetch(`http://localhost:8080/api/countries`)
    .then(response => response.json())
    .then(jsondata => {
      let tmpArray = []
      for (var i = 0; i < jsondata.length; i++) {
          tmpArray.push(jsondata[i])
      }
      setOptions(tmpArray)

    })
   }, []);

  return (
    <div className="container">
      <Header text={"COVID-19 Dashboard"}/>
      <Row>
        <Col style={{marginLeft:"5%"}}>
          <Dropdown label="Select a country" options={options} value={value} onChange={buttonChange}/>
          <br/>
          <p>From: </p>
          <ReactUTCDatepicker button={false} format={'YYYY-MM-DD'} date={startDate} onChange={date => setStartDate(date) }/>
          <br/>
          <br/>
          <p>To: </p>
          <ReactUTCDatepicker button={false} format={'YYYY-MM-DD'} date={endDate} onChange={date => setEndDate(date) }/>
          <br/>
          <br/>
          <Button type="button" variant="dark" onClick = {() => getHistory(value,startDate,endDate)} >See History</Button>
        </Col>
        <Col>
        <SmallContainer data={data} hasHistory={isHistory} historyData={historyData} days={days}/>
        </Col>
      </Row>

       
    </div>
  );
}

export default App;
