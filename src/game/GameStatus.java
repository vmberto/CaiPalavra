package game;

import javafx.beans.property.IntegerProperty;

public class GameStatus {

    private static int playerScore = 0;

    private static int playerLives = 3;

    private static boolean gameOver = false;

    private static int nextScoreFlag = 100;
    private static int flagsReached = 0;


    public static String[] wordsList = {
            "patience","teach","exemption","excuse","beach","unlikely","regret","hypothesize","sting","resolution","clear","democratic","album","manufacturer","guard","articulate","freshman","castle","willpower","major","ground","robot","capital","share","beautiful","bible","intention","agency","provision","slave","bucket","cotton","omission","sticky","talented","separate","dictate","determine","occupy","dragon","definite","assertive","sculpture","colorful","steak","stadium","stall","cheese","cooperation","classroom","plain","voyage","laborer","ankle","broccoli","permission","ribbon","slippery","radiation","emphasis","length","middle","grave","reluctance","blonde","revolution","generation","reach","harmful","quarrel","undertake","formula","likely","detail","torture","ethnic","necklace","index","offspring","thick","captivate","virus","dangerous","experiment","drive","scatter","persist","preference","clothes","swarm","abolish","vigorous","jewel","paint","cucumber","environment","crude","lesson","departure","perforate","horizon","greeting","correspond","mathematics","basic","conflict","timber","black","drain","suppress","vegetation","consumer","serve","leaflet","fantasy","prayer","seminar","address","favour","ideal","tired","exercise","application","convulsion","refund","house","defend","facility","exploit","college","straw","build","dairy","depressed","terrify","depression","feminist","liver","tribe","advocate","beneficiary","shift","community","rugby","arrange","petty","elite","patent","singer","debut","animal","transform","magnetic","bloodshed","penny","denial","shareholder","aloof","bathtub","default","grind","functional","assembly","cultural","healthy","carve","economic","admiration","carbon","shortage","dignity","overeat","difficult","copyright","sacred","choose","rescue","pocket","tight","pupil","stitch","domination","haircut","romantic","bubble","district","sunshine","dozen","arena","glacier","passage","contain","dimension","prosper","morsel","protection","reporter","active","science","hover","selection","estimate","mouse","strange","stock","appointment","throat","kidney","protect","distinct","count","safari","complication","offend","perfect","exchange","marathon","fireplace","wheat","frequency","explode","embrace","credit","deputy","consider","copper","coach","crouch","tablet","reactor","strict","visible","result","recession","constraint","concentration","pilot","quest","distort","useful","needle","ensure","bowel","aspect","pedestrian","psychology","siege","speech","solution","surface","monkey","horse","disaster","prosecution","operational","carpet","circle","wisecrack","offender","light","appetite","automatic","deserve","killer","border","thigh","deposit","neighborhood","tract","crowd","awful","fence","confuse","example","executrix","strong","craft","cheek","occupation","breed","report","pneumonia","extension","translate","funny","helmet","indoor","volume","activate","custody","mistreat","credibility","missile","reckless","institution","biography","bring","charm","empirical","consideration","participate","conception","paradox","virgin","shape","understanding","import","offer","objective","thesis","cinema","north","criminal","interference","sheep","ambition","blank","complex","danger","carrot","joint","horror","nervous","bridge","restaurant","frank","horseshoe","railroad","stomach","start","eject","literature","assault","prediction","exile","advantage","blade","interface","knife","short","monster","particle","tooth","total","problem","waterfall","bargain","preoccupation","trunk","publisher","rider","drink","office","superintendent","endorse","reservoir","admission","fastidious","whisper","linen","lifestyle","insert","consultation","punch","scrap","comfort","account","sanctuary","stroll","counter","common","behavior","reverse","insure","noise","combine","agent","platform","welcome","license","lunch","gossip","restless","drown","bracket","imposter","unlike","characteristic","refrigerator","compose","appoint","nonsense","print","toast","donor","front","influence","motorcycle","infrastructure","communication","pumpkin","spoil","acquit","confusion","hilarious","kinship","moment","building","memorial","exclusive","adult","speculate","crosswalk","march","justify","cruelty","asylum","inquiry","attachment","stand","presence","trend","anniversary","favor","clinic","telephone","remedy","evolution","multiply","domestic","discrimination","federation","tiger","seize","agreement","vegetable","allocation","direction","essay","ideology","still","digress","licence","voucher","desire","casualty","disappoint","confession","flourish","concrete","compete","payment","spectrum","source","firefighter","leash","hunter","medicine","estate","fountain","variable","torch","ostracize","frame","Koran","progressive","acceptance","relax","multimedia","coincidence","chord","future","agriculture","representative","absence","guerrilla","customer","civilian","proposal","cluster","dollar","circulate","committee","hypnothize","bounce","equinox","favourite","attack","conservative","uniform","researcher","contract","enthusiasm","hardware","review","minority","glide","money","discovery","investment","giant","grateful","volunteer","refer","instruction","buttocks","piece","dinner","assumption","vision","pepper","pledge","grandmother","judge","ambiguous","coincide","model","glimpse","tumble","assessment","failure","reflect","supplementary","recover","shell","construct","effective","progress","liberty","fossil","confine","hardship","mixture","coerce","syndrome","doubt","encourage","garlic","means","command","official","addition","chapter","cause","password","matter","background","flight","argument","hostile","suffer","trivial","topple","harsh","fashion","reasonable","incredible","similar","chemistry","deteriorate","polish","freighter","liberal","election","intervention","tension","finished","devote","mosquito","advance","training","investigation","education","rough","pigeon","important","contact","chauvinist","bullet","operation","moving","history","promotion","sequence","artificial","expand","squash","lemon","reform","manual","ballet","therapist","recruit","develop","thirsty","promote","delicate","econobox","earthflax","hospitality","global","claim","jelly","corner","economics","resident","enjoy","bother","theme","wonder","recommendation","staff","chain","sacrifice","stunning","midnight","violation","twitch","blast","peace","forget","sound","acceptable","bench","flash","screen","impulse","compensation","speed","patch","astonishing","merit","explosion","innovation","affinity","timetable","invisible","evoke","recycle","constant","dress","conviction","first","shaft","opera","hunting","revise","understand","suggest","close","medal","absent","retreat","ministry","worth","posture","fragment","basin","escape","member","extend","fibre","chocolate","highway","tumour","thought","distortion","lonely","cater","reliance","comedy","cupboard","recommend","dedicate","overall","order","snatch","scream","prize","flexible","agile","decrease","trace","development","factory","alcohol","cover","diplomat","retired","venus","burial","vague","expertise","energy","retiree","photography","feign","excess","misery","amber","option","theory","invasion","sleep","dilute","flavor","marketing","haunt","analysis","trial","snake","filter","steep","consensus","integrity","publish","struggle","weave","lover","disappointment","guess","cancer","marriage","printer","aluminium","association","family","purpose","gravity","muscle","treasurer","inflation","hypothesis","shallow","foster","sister","reception","judgment","implicit","outer","concession","promise","inspire","legislation","grounds","habitat","clarify","remember","steel","preparation","commemorate","dough","constitutional","entitlement","headquarters","bloody","digital","store","narrow","category","football","hobby","conservation","pursuit","abnormal","confront","tolerant","harvest","tournament","smile","specimen","relative","efflux","dribble","spine","positive","version","europe","expect","tiptoe","commitment","undermine","economist","stuff","yearn","heroin","paralyzed","calculation","respectable","exempt","creation","stimulation","rhetoric","shine","answer","affair","popular","title","alarm","gradual","witch","graze","proportion","elbow","large","stain","wound","remain","accessible","overwhelm","character","reality","growth","smooth","sweater","powder","restrict","adjust","press","strategic","collapse","lease","aviation","announcement","position","leadership","discuss","minimum","merchant","stable","genuine","survivor","arrogant","coast","detector","straight","weight","neighbour","heart","loose","collect","height","classify","survey","replacement","invite","cross","observer","fruit","overcharge","content","grand","feedback","conceive","slice","dismissal","unpleasant","critic","bless","route","hammer","abbey","musical","campaign","engine","indulge","conference","calorie","cherry","flour","ordinary","aquarium","organize","float","notion","consolidate","terminal","oppose","pasture","foundation","plagiarize","module","transport","extent","latest","accident","resource","young","particular","rotation","labour","avenue","society","canvas","increase","environmental","asset","break","solid","terrace","person","miracle","disagree","suspect","intensify","discriminate","aisle","cancel","obscure","wedding","velvet","budget","retire","obstacle","spite","place","insist","museum","facade","ceiling","ancestor","wreck","brave","revenge","soldier","proud","broadcast","childish","exaggerate","knock","congress","sheet","plane","overlook","novel","brother","perception","revival","delivery","conscious","acute","appendix","redundancy","bleed","adventure","scandal","lobby","taste","overview","practical","coffin","obligation","integration","greet","rotate","negative","touch","demonstration","instrument","skate","legislature","harbor","fairy","coffee","approve","cabin","contemporary","veteran","crown","computer","chase","register","twist","fault","image","circulation","guideline","medieval","drift","wardrobe","secretion","accumulation","bitter","surgeon","prove","flesh","manufacture","value"};

    public static void addScore(int points) {
        playerScore += points;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void subtractOneLife() {
        playerLives -= 1;
    }

    public static int getPlayerLives() {
        return playerLives;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver() {
        gameOver = true;
    }

    public static int getNextScoreFlag() { return nextScoreFlag; }

    public static void setNextScoreFlag() {
        nextScoreFlag += 100;
        flagsReached += 1;
    }

    public static void reset() {
        playerScore = 0;
        playerLives = 3;
        gameOver = false;
        nextScoreFlag = 100;
        flagsReached = 0;
    }

}
