/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import math.IntVector2;

/**
 *
 * @author rollersimmer
 */
public class AmericanStateMapFactory {
    
    private static StateMap result=null;
    
    private static void defineStates(){
        
        result.addUnique(new State(	/*index*/0	,/*name*/"Alabama"	,/*pos*/new IntVector2(-87,33)	,/*pop*/4779736	));
        result.addUnique(new State(	/*index*/1	,/*name*/"Alaska"	,/*pos*/new IntVector2(-149,61)	,/*pop*/710231	));
        result.addUnique(new State(	/*index*/2	,/*name*/"Arizona"	,/*pos*/new IntVector2(-112,33)	,/*pop*/6392017	));
        result.addUnique(new State(	/*index*/3	,/*name*/"Arkansas"	,/*pos*/new IntVector2(-93,35)	,/*pop*/2915918	));
        result.addUnique(new State(	/*index*/4	,/*name*/"California"	,/*pos*/new IntVector2(-119,35)	,/*pop*/37253956	));
        result.addUnique(new State(	/*index*/5	,/*name*/"Colorado"	,/*pos*/new IntVector2(-105,40)	,/*pop*/5029196	));
        result.addUnique(new State(	/*index*/6	,/*name*/"Connecticut"	,/*pos*/new IntVector2(-73,41)	,/*pop*/3574097	));
        result.addUnique(new State(	/*index*/7	,/*name*/"Delaware"	,/*pos*/new IntVector2(-76,39)	,/*pop*/897934	));
        result.addUnique(new State(	/*index*/8	,/*name*/"District of Columbia"	,/*pos*/new IntVector2(-77,39)	,/*pop*/601723	));
        result.addUnique(new State(	/*index*/9	,/*name*/"Florida"	,/*pos*/new IntVector2(-82,28)	,/*pop*/18801310	));
        result.addUnique(new State(	/*index*/10	,/*name*/"Georgia"	,/*pos*/new IntVector2(-84,33)	,/*pop*/9687653	));
        result.addUnique(new State(	/*index*/11	,/*name*/"Hawaii"	,/*pos*/new IntVector2(-157,21)	,/*pop*/1360301	));
        result.addUnique(new State(	/*index*/12	,/*name*/"Idaho"	,/*pos*/new IntVector2(-115,44)	,/*pop*/1567582	));
        result.addUnique(new State(	/*index*/13	,/*name*/"Illinois"	,/*pos*/new IntVector2(-88,41)	,/*pop*/12830632	));
        result.addUnique(new State(	/*index*/14	,/*name*/"Indiana"	,/*pos*/new IntVector2(-86,40)	,/*pop*/6483802	));
        result.addUnique(new State(	/*index*/15	,/*name*/"Iowa"	,/*pos*/new IntVector2(-93,42)	,/*pop*/3046355	));
        result.addUnique(new State(	/*index*/16	,/*name*/"Kansas"	,/*pos*/new IntVector2(-96,38)	,/*pop*/2853118	));
        result.addUnique(new State(	/*index*/17	,/*name*/"Kentucky"	,/*pos*/new IntVector2(-85,38)	,/*pop*/4339367	));
        result.addUnique(new State(	/*index*/18	,/*name*/"Louisiana"	,/*pos*/new IntVector2(-92,31)	,/*pop*/4533372	));
        result.addUnique(new State(	/*index*/19	,/*name*/"Maine"	,/*pos*/new IntVector2(-70,44)	,/*pop*/1328361	));
        result.addUnique(new State(	/*index*/20	,/*name*/"Maryland"	,/*pos*/new IntVector2(-77,39)	,/*pop*/5773552	));
        result.addUnique(new State(	/*index*/21	,/*name*/"Massachusetts"	,/*pos*/new IntVector2(-71,42)	,/*pop*/6547629	));
        result.addUnique(new State(	/*index*/22	,/*name*/"Michigan"	,/*pos*/new IntVector2(-84,43)	,/*pop*/9883640	));
        result.addUnique(new State(	/*index*/23	,/*name*/"Minnesota"	,/*pos*/new IntVector2(-94,45)	,/*pop*/5303925	));
        result.addUnique(new State(	/*index*/24	,/*name*/"Mississippi"	,/*pos*/new IntVector2(-90,33)	,/*pop*/2967297	));
        result.addUnique(new State(	/*index*/25	,/*name*/"Missouri"	,/*pos*/new IntVector2(-92,38)	,/*pop*/5988927	));
        result.addUnique(new State(	/*index*/26	,/*name*/"Montana"	,/*pos*/new IntVector2(-111,47)	,/*pop*/989415	));
        result.addUnique(new State(	/*index*/27	,/*name*/"Nebraska"	,/*pos*/new IntVector2(-97,41)	,/*pop*/1826341	));
        result.addUnique(new State(	/*index*/28	,/*name*/"Nevada"	,/*pos*/new IntVector2(-116,37)	,/*pop*/2700551	));
        result.addUnique(new State(	/*index*/29	,/*name*/"New Hampshire"	,/*pos*/new IntVector2(-71,43)	,/*pop*/1316470	));
        result.addUnique(new State(	/*index*/30	,/*name*/"New Jersey"	,/*pos*/new IntVector2(-74,40)	,/*pop*/8791894	));
        result.addUnique(new State(	/*index*/31	,/*name*/"New Mexico"	,/*pos*/new IntVector2(-106,35)	,/*pop*/2059179	));
        result.addUnique(new State(	/*index*/32	,/*name*/"New York"	,/*pos*/new IntVector2(-75,42)	,/*pop*/19378102	));
        result.addUnique(new State(	/*index*/33	,/*name*/"North Carolina"	,/*pos*/new IntVector2(-80,36)	,/*pop*/9535483	));
        result.addUnique(new State(	/*index*/34	,/*name*/"North Dakota"	,/*pos*/new IntVector2(-99,47)	,/*pop*/672591	));
        result.addUnique(new State(	/*index*/35	,/*name*/"Ohio"	,/*pos*/new IntVector2(-83,40)	,/*pop*/11536504	));
        result.addUnique(new State(	/*index*/36	,/*name*/"Oklahoma"	,/*pos*/new IntVector2(-97,36)	,/*pop*/3751351	));
        result.addUnique(new State(	/*index*/37	,/*name*/"Oregon"	,/*pos*/new IntVector2(-123,45)	,/*pop*/3831074	));
        result.addUnique(new State(	/*index*/38	,/*name*/"Pennsylvania"	,/*pos*/new IntVector2(-77,40)	,/*pop*/12702379	));
        result.addUnique(new State(	/*index*/39	,/*name*/"Puerto Rico"	,/*pos*/new IntVector2(-66,18)	,/*pop*/3725789	));
        result.addUnique(new State(	/*index*/40	,/*name*/"Rhode Island"	,/*pos*/new IntVector2(-71,42)	,/*pop*/1052567	));
        result.addUnique(new State(	/*index*/41	,/*name*/"South Carolina"	,/*pos*/new IntVector2(-81,34)	,/*pop*/4625364	));
        result.addUnique(new State(	/*index*/42	,/*name*/"South Dakota"	,/*pos*/new IntVector2(-99,44)	,/*pop*/814180	));
        result.addUnique(new State(	/*index*/43	,/*name*/"Tennessee"	,/*pos*/new IntVector2(-86,36)	,/*pop*/6346105	));
        result.addUnique(new State(	/*index*/44	,/*name*/"Texas"	,/*pos*/new IntVector2(-97,31)	,/*pop*/25145561	));
        result.addUnique(new State(	/*index*/45	,/*name*/"Utah"	,/*pos*/new IntVector2(-112,40)	,/*pop*/2763885	));
        result.addUnique(new State(	/*index*/46	,/*name*/"Vermont"	,/*pos*/new IntVector2(-73,44)	,/*pop*/625741	));
        result.addUnique(new State(	/*index*/47	,/*name*/"Virginia"	,/*pos*/new IntVector2(-78,38)	,/*pop*/8001024	));
        result.addUnique(new State(	/*index*/48	,/*name*/"Washington"	,/*pos*/new IntVector2(-122,47)	,/*pop*/6724540	));
        result.addUnique(new State(	/*index*/49	,/*name*/"West Virginia"	,/*pos*/new IntVector2(-81,39)	,/*pop*/1852994	));
        result.addUnique(new State(	/*index*/50	,/*name*/"Wisconsin"	,/*pos*/new IntVector2(-89,44)	,/*pop*/5686986	));
        result.addUnique(new State(	/*index*/51	,/*name*/"Wyoming"	,/*pos*/new IntVector2(-107,43)	,/*pop*/563626	));

    }

    private static void linkStates(){
        result.find("Alabama").setNeighbors("Tennessee","Florida","Georgia","Mississippi",result);
        result.find("Alaska").setNeighbors("","Washington","Montana","",result);
        result.find("Arizona").setNeighbors("Utah","","New Mexico","California",result);
        result.find("Arkansas").setNeighbors("Missouri","Louisiana","Mississippi","Oklahoma",result);
        result.find("California").setNeighbors("Oregon","Arizona","Nevada","Hawaii",result);
        result.find("Colorado").setNeighbors("Wyoming","New Mexico","Kansas","Utah",result);
        result.find("Connecticut").setNeighbors("Massachusetts","","Rhode Island","New York",result);
        result.find("Delaware").setNeighbors("Pennsylvania","","New Jersey","Maryland",result);
        result.find("District of Columbia").setNeighbors("Maryland","Virginia","Maryland","Virginia",result);
        result.find("Florida").setNeighbors("Georgia","Puerto Rico","Puerto Rico","Alabama",result);
        result.find("Georgia").setNeighbors("Tennessee","Florida","Alabama","South Carolina",result);
        result.find("Hawaii").setNeighbors("Oregon","","California","",result);
        result.find("Idaho").setNeighbors("Montana","Utah","Oregon","Wyoming",result);
        result.find("Illinois").setNeighbors("Wisconsin","Missouri","Indiana","Iowa",result);
        result.find("Indiana").setNeighbors("Michigan","Kentucky","Ohio","Illinois",result);
        result.find("Iowa").setNeighbors("Minnesota","Missouri","Illinois","Nebraska",result);
        result.find("Kansas").setNeighbors("Nebraska","Oklahoma","Missouri","Colorado",result);
        result.find("Kentucky").setNeighbors("Ohio","Tennessee","West Virginia","Indiana",result);
        result.find("Louisiana").setNeighbors("Arkansas","","Mississippi","Texas",result);
        result.find("Maine").setNeighbors("","Massachusetts","","New Hampshire",result);
        result.find("Maryland").setNeighbors("Pennsylvania","Virginia","Delaware","District of Columbia",result);
        result.find("Massachusetts").setNeighbors("New Hampshire","Connecticut","Rhode Island","New York",result);
        result.find("Michigan").setNeighbors("","Indiana","Ohio","Wisconsin",result);
        result.find("Minnesota").setNeighbors("North Dakota","Iowa","Wisconsin","South Dakota",result);
        result.find("Mississippi").setNeighbors("Tennessee","Louisiana","Alabama","Arkansas",result);
        result.find("Missouri").setNeighbors("Iowa","Arkansas","Illinois","Kansas",result);
        result.find("Montana").setNeighbors("North Dakota","Wyoming","South Dakota","Idaho",result);
        result.find("Nebraska").setNeighbors("South Dakota","Kansas","Iowa","Colorado",result);
        result.find("Nevada").setNeighbors("Idaho","Arizona","Utah","California",result);
        result.find("New Hampshire").setNeighbors("","Massachusetts","Maine","Vermont",result);
        result.find("New Jersey").setNeighbors("New York","Delaware","Pennsylvania","New York",result);
        result.find("New Mexico").setNeighbors("Colorado","Texas","Texas","Arizona",result);
        result.find("New York").setNeighbors("New Hampshire","New Jersey","Connecticut","Pennsylvania",result);
        result.find("North Carolina").setNeighbors("Virginia","South Carolina","","Tennessee",result);
        result.find("North Dakota").setNeighbors("","South Dakota","Minnesota","Montana",result);
        result.find("Ohio").setNeighbors("Michigan","Kentucky","Pennsylvania","Indiana",result);
        result.find("Oklahoma").setNeighbors("Kansas","Texas","Arkansas","Texas",result);
        result.find("Oregon").setNeighbors("Washington","Nevada","Idaho","California",result);
        result.find("Pennsylvania").setNeighbors("New York","Maryland","New Jersey","Ohio",result);
        result.find("Puerto Rico").setNeighbors("South Carolina","","Florida","",result);
        result.find("Rhode Island").setNeighbors("Massachusetts","","Massachusetts","Connecticut",result);
        result.find("South Carolina").setNeighbors("North Carolina","Georgia","Puerto Rico","Georgia",result);
        result.find("South Dakota").setNeighbors("North Dakota","Nebraska","Minnesota","Wyoming",result);
        result.find("Tennessee").setNeighbors("Kentucky","Alabama","Mississippi","Arkansas",result);
        result.find("Texas").setNeighbors("Oklahoma","","Louisiana","New Mexico",result);
        result.find("Utah").setNeighbors("Idaho","Arizona","Colorado","Nevada",result);
        result.find("Vermont").setNeighbors("","Massachusetts","New Hampshire","New York",result);
        result.find("Virginia").setNeighbors("District of Columbia","North Carolina","Maryland","West Virginia",result);
        result.find("Washington").setNeighbors("Alaska","Oregon","Idaho","",result);
        result.find("West Virginia").setNeighbors("Ohio","Virginia","Pennsylvania","Kentucky",result);
        result.find("Wisconsin").setNeighbors("Michigan","Illinois","","Minnesota",result);
        result.find("Wyoming").setNeighbors("Montana","Colorado","Utah","South Dakota",result);
    }
    
    public static StateMap create(){
        result=new StateMap();
        defineStates();
        linkStates();        
        return result;
    }

}