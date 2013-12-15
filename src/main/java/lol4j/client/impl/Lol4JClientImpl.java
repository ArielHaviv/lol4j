package lol4j.client.impl;

import lol4j.client.Lol4JClient;
import lol4j.protocol.dto.champion.ChampionListDto;
import lol4j.protocol.dto.game.RecentGamesDto;
import lol4j.protocol.dto.league.LeagueDto;
import lol4j.protocol.dto.stats.PlayerStatsSummaryListDto;
import lol4j.protocol.dto.stats.RankedStatsDto;
import lol4j.protocol.dto.summoner.MasteryPagesDto;
import lol4j.protocol.dto.summoner.RunePagesDto;
import lol4j.protocol.dto.summoner.SummonerDto;
import lol4j.protocol.dto.summoner.SummonerNameListDto;
import lol4j.protocol.dto.team.TeamDto;
import lol4j.protocol.resource.*;
import lol4j.protocol.resource.impl.ResourceFactory;
import lol4j.service.impl.ApiRequestManager;
import lol4j.util.Region;
import lol4j.util.Season;

import java.util.List;
import java.util.Map;

/**
 * Created by Aaryn101 on 12/10/13.
 */
public class Lol4JClientImpl implements Lol4JClient {
    private ChampionResource championResource;
    private GameResource gameResource;
    private LeagueResource leagueResource;
    private StatsResource statsResource;
    private SummonerResource summonerResource;
    private TeamResource teamResource;
    private ApiRequestManager apiRequestManager;

    public Lol4JClientImpl(String apiKey) {
        apiRequestManager = new ApiRequestManager(apiKey);
        ResourceFactory resourceFactory = new ResourceFactory(apiRequestManager);

        championResource = resourceFactory.createChampionResource();
        gameResource = resourceFactory.createGameResource();
        leagueResource = resourceFactory.createLeagueResource();
        statsResource = resourceFactory.createStatsResource();
        summonerResource = resourceFactory.createSummonerResource();
        teamResource = resourceFactory.createTeamResource();
    }

    @Override
    public ChampionListDto getAllChampions(Region region, boolean freeToPlay) {
        return championResource.getAllChampions(region, freeToPlay);
    }

    @Override
    public RecentGamesDto getRecentGames(Region region, long summonerId) {
        return gameResource.getRecentGames(region, summonerId);
    }

    @Override
    public Map<String, LeagueDto> getLeaguesData(Region region, long summonerId) {
        return leagueResource.getLeaguesData(region, summonerId);
    }

    @Override
    public PlayerStatsSummaryListDto getPlayerStatsSummaries(Region region, long summonerId, Season season) {
        return statsResource.getPlayerStatsSummaries(region, summonerId, season);
    }

    @Override
    public RankedStatsDto getRankedStats(Region region, long summonerId, Season season) {
        return statsResource.getRankedStats(region, summonerId, season);
    }

    @Override
    public MasteryPagesDto getMasteryPages(Region region, long summonerId) {
        return summonerResource.getMasteryPages(region, summonerId);
    }

    @Override
    public RunePagesDto getRunePages(Region region, long summonerId) {
        return summonerResource.getRunePages(region, summonerId);
    }

    @Override
    public SummonerDto getSummoner(Region region, String name) {
        return summonerResource.getSummoner(region, name);
    }

    @Override
    public SummonerDto getSummoner(Region region, long summonerId) {
        return summonerResource.getSummoner(region, summonerId);
    }

    @Override
    public SummonerNameListDto getSummonerNames(Region region, List<Long> summonerIds) {
        return summonerResource.getSummonerNames(region, summonerIds);
    }

    @Override
    public List<TeamDto> getTeams(Region region, long summonerId) {
        return teamResource.getTeams(region, summonerId);
    }

    @Override
    public void setRateLimit(int perTenSeconds, int perTenMinutes) {
        apiRequestManager.setRateLimit(perTenSeconds, perTenMinutes);
    }
}
