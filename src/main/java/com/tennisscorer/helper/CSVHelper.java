package com.tennisscorer.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.tennisscorer.model.TennisMatch;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "tourney_id", "tourney_name", "surface", "draw_size",
            "tourney_level", "tourney_date", "match_num", "winner_id", "winner_seed",
            "winner_name", "winner_hand", "winner_ht", "winner_ioc", "winner_age", "loser_id",
            "loser_seed", "loser_entry", "loser_name", "loser_hand", "loser_ht", "loser_ioc",
            "loser_age", "score", "best_of", "round", "minutes", "w_ace", "w_df", "w_svpt",
            "w_1stIn", "w_1stWon", "w_2ndWon", "w_SvGms", "w_bpSaved", "w_bpFaced" , "l_ace",
            "l_df", "l_svpt", "l_1stIn", "l_1stWon","l_2ndWon", "l_SvGms", "l_bpSaved",
            "l_bpFaced", "winner_rank", "winner_rank_points","loser_rank", "loser_rank_points"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<TennisMatch> csvToTennisMatch(InputStream is){
        List<TennisMatch> tennisMatches = new ArrayList<TennisMatch>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){


            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                TennisMatch tennisMatch = new TennisMatch(
                        csvRecord.get("tourney_id"),
                        csvRecord.get("tourney_name"),
                        csvRecord.get("surface"),
                        Long.parseLong(!csvRecord.get("draw_size").equals("")? csvRecord.get("draw_size") : "0"),
                        csvRecord.get("tourney_level"),
                        csvRecord.get("tourney_date"),
                        Integer.parseInt( !csvRecord.get("match_num").equals("") ? csvRecord.get("match_num") : "0"),
                        Long.parseLong(!csvRecord.get("winner_id").equals("") ? csvRecord.get("winner_id") : "0"),
                        Integer.parseInt( !csvRecord.get("winner_seed").equals("") ? csvRecord.get("winner_seed") : "0" ),
                        csvRecord.get("winner_entry"),
                        csvRecord.get("winner_name"),
                        csvRecord.get("winner_hand"),
                        Integer.parseInt(!csvRecord.get("winner_ht").equals("") ? csvRecord.get("winner_ht") : "0"),
                        csvRecord.get("winner_ioc"),
                        Double.parseDouble(!csvRecord.get("winner_age").equals("") ? csvRecord.get("winner_age") : "0"),
                        Long.parseLong(!csvRecord.get("loser_id").equals("") ? csvRecord.get("loser_id") : "0"),
                        Integer.parseInt(!csvRecord.get("loser_seed").equals("") ? csvRecord.get("loser_seed") : "0"),
                        csvRecord.get("loser_entry"),
                        csvRecord.get("loser_name"),
                        csvRecord.get("loser_hand"),
                        csvRecord.get("loser_ioc"),
                        Integer.parseInt(!csvRecord.get("loser_ht").equals("") ? csvRecord.get("loser_ht") : "0"),
                        Double.parseDouble(!csvRecord.get("loser_age").equals("") ? csvRecord.get("loser_age") : "0"),
                        csvRecord.get("score"),
                        Integer.parseInt(!csvRecord.get("best_of").equals("") ? csvRecord.get("best_of") : "0"),
                        csvRecord.get("round"),
                        Integer.parseInt( !csvRecord.get("minutes").equals("") ? csvRecord.get("minutes") : "0"),
                        Integer.parseInt( !csvRecord.get("w_ace").equals("") ? csvRecord.get("w_ace") : "0"),
                        Integer.parseInt( !csvRecord.get("w_df").equals("") ? csvRecord.get("w_df") : "0"),
                        Integer.parseInt( !csvRecord.get("w_svpt").equals("") ? csvRecord.get("w_svpt") : "0"),
                        Integer.parseInt( !csvRecord.get("w_1stIn").equals("") ? csvRecord.get("w_1stIn") : "0"),
                        Integer.parseInt( !csvRecord.get("w_1stWon").equals("") ? csvRecord.get("w_1stWon") : "0"),
                        Integer.parseInt(!csvRecord.get("w_2ndWon").equals("") ? csvRecord.get("w_2ndWon") : "0"),
                        Integer.parseInt( !csvRecord.get("w_SvGms").equals("") ? csvRecord.get("w_SvGms") : "0"),
                        Integer.parseInt(!csvRecord.get("w_bpSaved").equals("") ? csvRecord.get("w_bpSaved"): "0"),
                        Integer.parseInt(!csvRecord.get("w_bpFaced").equals("")  ? csvRecord.get("w_bpFaced"): "0"),
                        Integer.parseInt(!csvRecord.get("l_ace").equals("") ? csvRecord.get("l_ace"): "0"),
                        Integer.parseInt(!csvRecord.get("l_df").equals("") ? csvRecord.get("l_df"): "0"),
                        Integer.parseInt(!csvRecord.get("l_svpt").equals("") ? csvRecord.get("l_svpt"): "0"),
                        Integer.parseInt(!csvRecord.get("l_1stIn").equals("") ? csvRecord.get("l_1stIn"): "0"),
                        Integer.parseInt(!csvRecord.get("l_1stWon").equals("") ? csvRecord.get("l_1stWon"): "0"),
                        Integer.parseInt( !csvRecord.get("l_2ndWon").equals("") ?csvRecord.get("l_2ndWon"): "0"),
                        Integer.parseInt(!csvRecord.get("l_SvGms").equals("") ? csvRecord.get("l_SvGms"): "0"),
                        Integer.parseInt(!csvRecord.get("l_bpSaved").equals("") ? csvRecord.get("l_bpSaved"): "0"),
                        Integer.parseInt(!csvRecord.get("l_bpFaced").equals("") ? csvRecord.get("l_bpFaced"): "0"),
                        Integer.parseInt(!csvRecord.get("winner_rank").equals("") ? csvRecord.get("winner_rank"): "0"),
                        Integer.parseInt(!csvRecord.get("winner_rank_points").equals("") ? csvRecord.get("winner_rank_points"): "0"),
                        Integer.parseInt(!csvRecord.get("loser_rank").equals("") ? csvRecord.get("loser_rank"): "0"),
                        Integer.parseInt(!csvRecord.get("loser_rank_points").equals("") ? csvRecord.get("loser_rank_points"): "0")
                );
                tennisMatches.add(tennisMatch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tennisMatches;
    }

    public static ByteArrayInputStream tennisMatchToCsv(List<TennisMatch> tennisMatches){
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for(TennisMatch tennisMatch : tennisMatches){
                List<String> data = Arrays.asList(
                        String.valueOf(tennisMatch.getId()),
                        tennisMatch.getTourneyId(),
                        tennisMatch.getTourney_name(),
                        tennisMatch.getSurface(),
                        String.valueOf(tennisMatch.getDraw_size()),
                        tennisMatch.getTourney_level(),
                        tennisMatch.getTourney_date(),
                        String.valueOf(tennisMatch.getMatch_num()),
                        String.valueOf(tennisMatch.getWinner_id()),
                        String.valueOf(tennisMatch.getWinner_seed()),
                        String.valueOf(tennisMatch.getWinner_entry()),
                        tennisMatch.getWinner_name(),
                        tennisMatch.getWinner_hand(),
                        String.valueOf(tennisMatch.getWinner_ht()),
                        tennisMatch.getWinner_ioc(),
                        String.valueOf(tennisMatch.getWinner_age()),
                        String.valueOf(tennisMatch.getLoser_id()),
                        String.valueOf(tennisMatch.getLoser_seed()),
                        String.valueOf(tennisMatch.getLoser_entry()),
                        tennisMatch.getLoser_name(),
                        tennisMatch.getLoser_hand(),
                        tennisMatch.getLoser_ioc(),
                        String.valueOf(tennisMatch.getLoser_ht()),
                        String.valueOf(tennisMatch.getLoser_age()),
                        tennisMatch.getScore(),
                        String.valueOf(tennisMatch.getBest_of()),
                        String.valueOf(tennisMatch.getRound()),
                        String.valueOf(tennisMatch.getMinutes()),
                        String.valueOf(tennisMatch.getW_ace()),
                        String.valueOf(tennisMatch.getW_df()),
                        String.valueOf(tennisMatch.getW_svpt()),
                        String.valueOf(tennisMatch.getW_1st_in()),
                        String.valueOf(tennisMatch.getW_1st_won()),
                        String.valueOf(tennisMatch.getW_2nd_won()),
                        String.valueOf(tennisMatch.getW_sv_gms()),
                        String.valueOf(tennisMatch.getW_bp_saved()),
                        String.valueOf(tennisMatch.getW_bp_faced()),
                        String.valueOf(tennisMatch.getL_ace()),
                        String.valueOf(tennisMatch.getL_df()),
                        String.valueOf(tennisMatch.getL_svpt()),
                        String.valueOf(tennisMatch.getL_1st_in()),
                        String.valueOf(tennisMatch.getL_1st_won()),
                        String.valueOf(tennisMatch.getL_2nd_won()),
                        String.valueOf(tennisMatch.getL_sv_gms()),
                        String.valueOf(tennisMatch.getL_bp_saved()),
                        String.valueOf(tennisMatch.getL_bp_faced()),
                        String.valueOf(tennisMatch.getL_bp_saved()),
                        String.valueOf(tennisMatch.getWinner_rank()),
                        String.valueOf(tennisMatch.getWinner_rank_points()),
                        String.valueOf(tennisMatch.getLoser_rank()),
                        String.valueOf(tennisMatch.getLoser_rank_points())
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }

    }

}
