import React, { useMemo, useState, useEffect } from "react";
import Table from "./Table";
import Card from 'react-bootstrap/Card'
import './Home.css'

function Home () {
    function reformatPrices(prices) {
      let result = [{"coin": {}}, {"coin": {}}]
      result[0].coin.name = "Etheureum"
      result[0].coin.buy = "$" + prices.ethereumBuy
      result[0].coin.sell = "$" + prices.ethereumSell
      result[1].coin.name = "Bitcoin"
      result[1].coin.buy = "$" + prices.bitcoinBuy
      result[1].coin.sell = "$" + prices.bitcoinSell
      return result
    }

    function calculateRec(coinbasePrices, binancePrices) {
      let result = {"bitcoin": [null, null], "etheureum": [null, null]}
      if (coinbasePrices.length == 0 || binancePrices.length == 0) {
        return result;
      }
      //etheureum buy calculation
      if (coinbasePrices[0].coin.buy <=  binancePrices[0].coin.buy) {
        result.etheureum[0] = ("Coinbase")
      }
      else {
        result.etheureum[0] = ("Binance")
      }
      //etheureum sell caclulation
      if (coinbasePrices[0].coin.sell >=  binancePrices[0].coin.sell) {
        result.etheureum[1] = ("Coinbase")
      }
      else {
        result.etheureum[1] = ("Binance")
      }
      //bitcoin buy calculation
      if (coinbasePrices[1].coin.buy <=  binancePrices[1].coin.buy) {
        result.bitcoin[0] = ("Coinbase")
      }
      else {
        result.bitcoin[0] = ("Binance")
      }
      //bitcoin sell caclulation
      if (coinbasePrices[1].coin.sell >=  binancePrices[1].coin.sell) {
        result.bitcoin[1] = ("Coinbase")
      }
      else {
        result.bitcoin[1] = ("Binance")
      }
      return result
    }

    
    const [coinbasePrices, setCoinbasePrices] = useState([]);
    const [binancePrices, setBinancePrices] = useState([]);
    const [recommendations, setRecommendations] = useState({"bitcoin": [null, null], "etheureum": [null, null]});

    useEffect(() => {
      let isMounted = true; 
        fetch("http://localhost:8080/prices/coinbase")
          .then((res) => res.json())
          .then((data) => {
            if (isMounted)
              setCoinbasePrices(reformatPrices(data));
          })
          .catch((err) => {
            throw err;
          });
        fetch("http://localhost:8080/prices/binance")
          .then((res) => res.json())
          .then((data) => {
            if (isMounted)
              setBinancePrices(reformatPrices(data));
          })
          .catch((err) => {
            throw err;
          });
          return () => { isMounted = false }; 
          
    }, []);

    useEffect(() => {
      if (coinbasePrices && binancePrices) {
        setRecommendations(calculateRec(coinbasePrices, binancePrices));
      }
    }, [coinbasePrices, binancePrices]);

    const columns1 = useMemo(
      () => [
        {
          Header: "Coinbase",
          // First group columns
          columns: [
            {
              Header: "Currency",
              accessor: "coin.name"
            },
            {
              Header: "Buy",
              accessor: "coin.buy"
            },
            {
              Header: "Sell",
              accessor: "coin.sell"
            }
          ]
        }
      ],
      []
    );
    const columns2 = useMemo(
      () => [
        {
          Header: "Binance",
          // First group columns
          columns: [
            {
              Header: "Currency",
              accessor: "coin.name"
            },
            {
              Header: "Buy",
              accessor: "coin.buy"
            },
            {
              Header: "Sell",
              accessor: "coin.sell"
            }
          ]
        }
      ],
      []
    );

    return (
      <div className="home-section" id="home">
        <div className="row1">
          <div>
            <Card className="coinBaseCard" >
              <Card.Header className="recommendationCardHeader">
                <h1>Exchange 1</h1>
              </Card.Header>
              <Card.Body className="coinbaseCardBody">
                  <Table columns={columns1} data={coinbasePrices} />
              </Card.Body>
              <Card.Footer className="text-muted"></Card.Footer>
            </Card>
          </div>
          <div>  
            <Card className="binanceCard">
              <Card.Header className="recommendationCardHeader">
                <h1>Exchange 2</h1>
              </Card.Header>
              <Card.Body className="binanceCardBody">
                  <Table columns={columns2} data={binancePrices} />
              </Card.Body>
              <Card.Footer className="text-muted"></Card.Footer>
            </Card>
          </div>
        </div>
        <div className="row2">
          <Card className="recommendationCard">
            <Card.Header className="recommendationCardHeader">
              <h1>Recommendation</h1>
            </Card.Header>
            <Card.Body className="recommendationCardBody">
              <p> Buy Bitcoin at: {recommendations.bitcoin[0]}</p>
              <p> Sell Bitcoin at: {recommendations.bitcoin[1]}</p>
              <p> Buy Etheureum at: {recommendations.etheureum[0]}</p>
              <p> Sell Etheureum at: {recommendations.etheureum[1]}</p>
            </Card.Body>
            <Card.Footer className="text-muted"></Card.Footer>
          </Card>
        </div>
      </div>       
    );
    
}

export default Home;