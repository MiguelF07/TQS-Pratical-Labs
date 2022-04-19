import React from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import {useState,useEffect} from 'react'

function DisplayHistory({historyData,days}) {
    let hasData=false
    let tmp;
    let history = [];
    //const [history, setHistory] = useState([]);
    if(historyData!=="No History Data")
    {
        tmp = JSON.parse(historyData)
        hasData=true
        for(let i=0; i<=days;i++) {
          history.push(tmp[i])
        }
        console.log("HISTORICO")
        console.log(history.toString())
    }

    const displayStuff = () => {
      let teste = Object.assign([],history)
      history= Object.assign([])
      return <>
      {teste.map((stat,index) => (
        <>
        <b>Continent: </b> {stat.continent===null ? <i>Null</i> : stat.continent} <br/>
        <b>Country: </b> {stat.country===null ? <i>Null</i> : stat.country} <br/>
        <b>Population: </b> {stat.population===null ? <i>Null</i> : stat.population} <br/>
        <b>Day: </b> {stat.day===null ? <i>Null</i> : stat.day} <br/>
        <b>Time: </b> {stat.time===null ? <i>Null</i> : stat.time} <br/>
        <br/>
        <b>New Cases: </b> {stat.cases.newCases===null ? <i>Null</i> : stat.cases.newCases} <br/>
        <b>Active Cases: </b> {stat.cases.active===null ? <i>Null</i> : stat.cases.active} <br/>
        <b>Critical Cases: </b> {stat.cases.critical===null ? <i>Null</i> : stat.cases.critical} <br/>
        <b>Recovered Cases: </b> {stat.cases.recovered===null ? <i>Null</i> : stat.cases.recovered} <br/>
        <b>Cases per 1M: </b> {stat.cases.oneM_pop===null ? <i>Null</i> : stat.cases.oneM_pop} <br/>
        <b>Total Cases: </b> {stat.cases.total===null ? <i>Null</i> : stat.cases.total} <br/>
        <br/>
        <b>New Deaths: </b> {stat.deaths.newDeaths===null ? <i>Null</i> : stat.deaths.newDeaths} <br/>
        <b>Deaths per 1M: </b> {stat.deaths.oneM_pop===null ? <i>Null</i> : stat.deaths.oneM_pop} <br/>
        <b>Total Deaths: </b> {stat.deaths.total===null ? <i>Null</i> : stat.deaths.total} <br/>
        <br/>
        <b>Tests per 1M: </b> {stat.tests.oneM_pop===null ? <i>Null</i> : stat.tests.oneM_pop} <br/>
        <b>Total Tests: </b> {stat.tests.total===null ? <i>Null</i> : stat.tests.total} <br/>
        <br/>
        <b>--------------------------------</b><br/>
        </>
      ))}
      </>
    }
  return (
    <Row>
        <Col>
        <p>{hasData ? "": "No Data To Show"}</p>
        {hasData && history && displayStuff()}
        </Col>
    </Row>
  )
}

export default DisplayHistory