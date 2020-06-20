package com.covid.cti.services;

import com.covid.cti.models.CovidData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidTrackerService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/imdevskp/covid-19-india-data/master/state_level_latest.csv";

    private List<CovidData> allStats = new ArrayList<>();
    private int totalCases;

    public List<CovidData> getAllStats() {
        return allStats;
    }

    @PostConstruct
    public void fetchCovidData() throws IOException, InterruptedException {
        List<CovidData> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            if(record.get("State") == "Total"){
                totalCases = Integer.parseInt(record.get("Confirmed"));
            }
            else {
                CovidData locationStat = new CovidData();
                locationStat.setState(record.get("State"));
                locationStat.setConfirmed(Integer.parseInt(record.get("Confirmed")));
                locationStat.setConfirmedRise(Integer.parseInt(record.get("Delta_Confirmed")));
                locationStat.setActive(Integer.parseInt(record.get("Active")));
                locationStat.setRecovered(Integer.parseInt(record.get("Recovered")));
                newStats.add(locationStat);
            }
        }
        this.allStats = newStats;

    }

}
