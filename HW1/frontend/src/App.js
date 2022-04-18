import './App.css';
import Header from './components/Header';
import Dropdown from './components/Dropdown';
import SmallContainer from './components/SmallContainer';
import {useState,useEffect} from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'

function App() {

  const [value, setValue] = useState();
  const [options,setOptions] = useState([]);
  const [data,setData] = useState("No Data");

  const fetchStatistics = (value) => {
//Fazer o value concatenar com o fetch e fazer com que o data seja updated
    var lowerValue = value.toLowerCase();
    return fetch(`http://localhost:8080/api/statistics/${lowerValue}`)
    .then((response => response.json()))
    // .then((jsondata) => {
    //   console.log(jsondata)
    //   setData(jsondata)
    //   // console.log(data)
    // })
    // .then(jsondata => {return jsondata})
  };

  const getStatistics = (value) => {
    return (fetchStatistics(value))
  }

  const handleChange = (event) => {
    setValue(event.target.value);
    getStatistics(event.target.value).then((dataReceived) => {
      console.log("Hey")
      console.log(dataReceived)
      let tmp = JSON.stringify(dataReceived)
      //let tmp = JSON.parse(dataReceived)
      setData(tmp)
    })
  };


  //Fetch the countries
  useEffect(() => {
    fetch(`http://localhost:8080/api/countries`)
    .then(response => response.json())
    //.then(jsondata => console.log(jsondata))
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
      <Header text={"COVID-19 Statistics"}/>
      <Row>
        <Col>
          <Dropdown label="Select a country" options={options} value={value} onChange={handleChange}/>
        </Col>
        <Col>
        <SmallContainer data={data}/>
        </Col>
      </Row>

       
    </div>
  );
}

export default App;
