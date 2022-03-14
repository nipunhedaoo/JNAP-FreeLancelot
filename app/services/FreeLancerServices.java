package services;

import models.ProjectDetails;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.*;

public class FreeLancerServices {

    String API = "https://www.freelancer.com/api/";
    static Scanner sc = new Scanner(System.in);


    public HashMap<String, List<ProjectDetails>> searchResults(String phrase, HashMap<String, List<ProjectDetails>> searchResults)
    {
        List<ProjectDetails> array = new ArrayList<>();
        try {
            URL url = new URL(API + "projects/0.1/projects/active?query=\""+ phrase +"\"&limit=10&job_details=true");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                String temp="";
                while(scan.hasNext()) {
                    temp = temp + scan.nextLine();
                }
                JSONObject json = new JSONObject(temp);
                JSONObject result = json.getJSONObject("result");
                JSONArray projects = (JSONArray) result.getJSONArray("projects");

                for (int i = 0; i < projects.length() ; i++){
                    JSONObject object = projects.getJSONObject(i);

                    long ownerId =  Long.parseLong(object.get("owner_id").toString());
                    long timeSubmitted = Long.parseLong(object.get("submitdate").toString());
                    String title = object.get("title").toString() ;
                    String type = object.get("type").toString();
                    String projectDescription = object.get("preview_description").toString();

                    JSONArray skills = object.getJSONArray("jobs");
                    List <String> skillsList = new ArrayList<>();
                    for( int j=0; j<skills.length(); j++){
                        JSONObject skillObj = skills.getJSONObject(j);
                        skillsList.add(skillObj.get("name").toString());
                    }
                    array.add(new ProjectDetails(ownerId, skillsList, timeSubmitted, title, type, projectDescription));
                }
            }

            searchResults.put(phrase , array);
        } catch (Exception e) {
        }
        readabilityIndex(phrase, searchResults);
        return searchResults;
    }

    public static char lastChar (String aWord)
    {
        char aChar = aWord.charAt(aWord.length() - 2);
        System.out.print (aChar);
        return aChar;
    }

    public double readabilityIndex(String phrase, HashMap<String, List<ProjectDetails>> searchResults)
    {


        Double searchResultUpadted = searchResults.get(phrase).stream().mapToDouble(project -> {
            double fkcl = 0;
            double fkgl = 0;
            int numOfSentence = 5;
            int numOfWords = 0;
            int numOfSyllables = 0;
            String educationalLevel = "";

            String projectDescription = "\tROMEO AND JULIET\n" +
                    "\n" +
                    "\tDRAMATIS PERSONAE\n" +
                    "\n" +
                    "ESCALUS\tprince of Verona. (PRINCE:)\n" +
                    "\n" +
                    "PARIS\ta young nobleman, kinsman to the prince.\n" +
                    "\n" +
                    "MONTAGUE\t|\n" +
                    "\t|  heads of two houses at variance with each other.\n" +
                    "CAPULET\t|\n" +
                    "\n" +
                    "\tAn old man, cousin to Capulet. (Second Capulet:)\n" +
                    "\n" +
                    "ROMEO\tson to Montague.\n" +
                    "\n" +
                    "MERCUTIO\tkinsman to the prince, and friend to Romeo.\n" +
                    "\n" +
                    "BENVOLIO\tnephew to Montague, and friend to Romeo.\n" +
                    "\n" +
                    "TYBALT\tnephew to Lady Capulet.\n" +
                    "\n" +
                    "FRIAR LAURENCE\t|\n" +
                    "\t|  Franciscans.\n" +
                    "FRIAR JOHN\t|\n" +
                    "\n" +
                    "BALTHASAR\tservant to Romeo.\n" +
                    "\n" +
                    "SAMPSON\t|\n" +
                    "\t|  servants to Capulet.\n" +
                    "GREGORY\t|\n" +
                    "\n" +
                    "PETER\tservant to Juliet's nurse.\n" +
                    "\n" +
                    "ABRAHAM\tservant to Montague.\n" +
                    "\n" +
                    "\tAn Apothecary. (Apothecary:)\n" +
                    "\n" +
                    "\tThree Musicians.\n" +
                    "\t(First Musician:)\n" +
                    "\t(Second Musician:)\n" +
                    "\t(Third Musician:)\n" +
                    "\n" +
                    "\tPage to Paris; (PAGE:)  another Page; an officer.\n" +
                    "\n" +
                    "LADY MONTAGUE\twife to Montague.\n" +
                    "\n" +
                    "LADY CAPULET\twife to Capulet.\n" +
                    "\n" +
                    "JULIET\tdaughter to Capulet.\n" +
                    "\n" +
                    "\tNurse to Juliet. (Nurse:)\n" +
                    "\n" +
                    "\tCitizens of Verona; several Men and Women,\n" +
                    "\trelations to both houses; Maskers,\n" +
                    "\tGuards, Watchmen, and Attendants.\n" +
                    "\t(First Citizen:)\n" +
                    "\t(Servant:)\n" +
                    "\t(First Servant:)\n" +
                    "\t(Second Servant:)\n" +
                    "\t(First Watchman:)\n" +
                    "\t(Second Watchman:)\n" +
                    "\t(Third Watchman:)\n" +
                    "\tChorus.\n" +
                    "\n" +
                    "SCENE\tVerona: Mantua.\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "\tPROLOGUE\n" +
                    "\n" +
                    "\tTwo households, both alike in dignity,\n" +
                    "\tIn fair Verona, where we lay our scene,\n" +
                    "\tFrom ancient grudge break to new mutiny,\n" +
                    "\tWhere civil blood makes civil hands unclean.\n" +
                    "\tFrom forth the fatal loins of these two foes\n" +
                    "\tA pair of star-cross'd lovers take their life;\n" +
                    "\tWhole misadventured piteous overthrows\n" +
                    "\tDo with their death bury their parents' strife.\n" +
                    "\tThe fearful passage of their death-mark'd love,\n" +
                    "\tAnd the continuance of their parents' rage,\n" +
                    "\tWhich, but their children's end, nought could remove,\n" +
                    "\tIs now the two hours' traffic of our stage;\n" +
                    "\tThe which if you with patient ears attend,\n" +
                    "\tWhat here shall miss, our toil shall strive to mend.\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT I\n" +
                    "\n" +
                    "SCENE I\tVerona. A public place.\n" +
                    "\n" +
                    "\t[Enter SAMPSON and GREGORY, of the house of Capulet,\n" +
                    "\tarmed with swords and bucklers]\n" +
                    "\n" +
                    "SAMPSON\tGregory, o' my word, we'll not carry coals.\n" +
                    "\n" +
                    "GREGORY\tNo, for then we should be colliers.\n" +
                    "\n" +
                    "SAMPSON\tI mean, an we be in choler, we'll draw.\n" +
                    "\n" +
                    "GREGORY\tAy, while you live, draw your neck out o' the collar.\n" +
                    "\n" +
                    "SAMPSON\tI strike quickly, being moved.\n" +
                    "\n" +
                    "GREGORY\tBut thou art not quickly moved to strike.\n" +
                    "\n" +
                    "SAMPSON\tA dog of the house of Montague moves me.\n" +
                    "\n" +
                    "GREGORY\tTo move is to stir; and to be valiant is to stand:\n" +
                    "\ttherefore, if thou art moved, thou runn'st away.\n" +
                    "\n" +
                    "SAMPSON\tA dog of that house shall move me to stand: I will\n" +
                    "\ttake the wall of any man or maid of Montague's.\n" +
                    "\n" +
                    "GREGORY\tThat shows thee a weak slave; for the weakest goes\n" +
                    "\tto the wall.\n" +
                    "\n" +
                    "SAMPSON\tTrue; and therefore women, being the weaker vessels,\n" +
                    "\tare ever thrust to the wall: therefore I will push\n" +
                    "\tMontague's men from the wall, and thrust his maids\n" +
                    "\tto the wall.\n" +
                    "\n" +
                    "GREGORY\tThe quarrel is between our masters and us their men.\n" +
                    "\n" +
                    "SAMPSON\t'Tis all one, I will show myself a tyrant: when I\n" +
                    "\thave fought with the men, I will be cruel with the\n" +
                    "\tmaids, and cut off their heads.\n" +
                    "\n" +
                    "GREGORY\tThe heads of the maids?\n" +
                    "\n" +
                    "SAMPSON\tAy, the heads of the maids, or their maidenheads;\n" +
                    "\ttake it in what sense thou wilt.\n" +
                    "\n" +
                    "GREGORY\tThey must take it in sense that feel it.\n" +
                    "\n" +
                    "SAMPSON\tMe they shall feel while I am able to stand: and\n" +
                    "\t'tis known I am a pretty piece of flesh.\n" +
                    "\n" +
                    "GREGORY\t'Tis well thou art not fish; if thou hadst, thou\n" +
                    "\thadst been poor John. Draw thy tool! here comes\n" +
                    "\ttwo of the house of the Montagues.\n" +
                    "\n" +
                    "SAMPSON\tMy naked weapon is out: quarrel, I will back thee.\n" +
                    "\n" +
                    "GREGORY\tHow! turn thy back and run?\n" +
                    "\n" +
                    "SAMPSON\tFear me not.\n" +
                    "\n" +
                    "GREGORY\tNo, marry; I fear thee!\n" +
                    "\n" +
                    "SAMPSON\tLet us take the law of our sides; let them begin.\n" +
                    "\n" +
                    "GREGORY\tI will frown as I pass by, and let them take it as\n" +
                    "\tthey list.\n" +
                    "\n" +
                    "SAMPSON\tNay, as they dare. I will bite my thumb at them;\n" +
                    "\twhich is a disgrace to them, if they bear it.\n" +
                    "\n" +
                    "\t[Enter ABRAHAM and BALTHASAR]\n" +
                    "\n" +
                    "ABRAHAM\tDo you bite your thumb at us, sir?\n" +
                    "\n" +
                    "SAMPSON\tI do bite my thumb, sir.\n" +
                    "\n" +
                    "ABRAHAM\tDo you bite your thumb at us, sir?\n" +
                    "\n" +
                    "SAMPSON\t[Aside to GREGORY]  Is the law of our side, if I say\n" +
                    "\tay?\n" +
                    "\n" +
                    "GREGORY\tNo.\n" +
                    "\n" +
                    "SAMPSON\tNo, sir, I do not bite my thumb at you, sir, but I\n" +
                    "\tbite my thumb, sir.\n" +
                    "\n" +
                    "GREGORY\tDo you quarrel, sir?\n" +
                    "\n" +
                    "ABRAHAM\tQuarrel sir! no, sir.\n" +
                    "\n" +
                    "SAMPSON\tIf you do, sir, I am for you: I serve as good a man as you.\n" +
                    "\n" +
                    "ABRAHAM\tNo better.\n" +
                    "\n" +
                    "SAMPSON\tWell, sir.\n" +
                    "\n" +
                    "GREGORY\tSay 'better:' here comes one of my master's kinsmen.\n" +
                    "\n" +
                    "SAMPSON\tYes, better, sir.\n" +
                    "\n" +
                    "ABRAHAM\tYou lie.\n" +
                    "\n" +
                    "SAMPSON\tDraw, if you be men. Gregory, remember thy swashing blow.\n" +
                    "\n" +
                    "\t[They fight]\n" +
                    "\n" +
                    "\t[Enter BENVOLIO]\n" +
                    "\n" +
                    "BENVOLIO\tPart, fools!\n" +
                    "\tPut up your swords; you know not what you do.\n" +
                    "\n" +
                    "\t[Beats down their swords]\n" +
                    "\n" +
                    "\t[Enter TYBALT]\n" +
                    "\n" +
                    "TYBALT\tWhat, art thou drawn among these heartless hinds?\n" +
                    "\tTurn thee, Benvolio, look upon thy death.\n" +
                    "\n" +
                    "BENVOLIO\tI do but keep the peace: put up thy sword,\n" +
                    "\tOr manage it to part these men with me.\n" +
                    "\n" +
                    "TYBALT\tWhat, drawn, and talk of peace! I hate the word,\n" +
                    "\tAs I hate hell, all Montagues, and thee:\n" +
                    "\tHave at thee, coward!\n" +
                    "\n" +
                    "\t[They fight]\n" +
                    "\n" +
                    "\t[Enter, several of both houses, who join the fray;\n" +
                    "\tthen enter Citizens, with clubs]\n" +
                    "\n" +
                    "First Citizen\tClubs, bills, and partisans! strike! beat them down!\n" +
                    "\tDown with the Capulets! down with the Montagues!\n" +
                    "\n" +
                    "\t[Enter CAPULET in his gown, and LADY CAPULET]\n" +
                    "\n" +
                    "CAPULET\tWhat noise is this? Give me my long sword, ho!\n" +
                    "\n" +
                    "LADY CAPULET\tA crutch, a crutch! why call you for a sword?\n" +
                    "\n" +
                    "CAPULET\tMy sword, I say! Old Montague is come,\n" +
                    "\tAnd flourishes his blade in spite of me.\n" +
                    "\n" +
                    "\t[Enter MONTAGUE and LADY MONTAGUE]\n" +
                    "\n" +
                    "MONTAGUE\tThou villain Capulet,--Hold me not, let me go.\n" +
                    "\n" +
                    "LADY MONTAGUE\tThou shalt not stir a foot to seek a foe.\n" +
                    "\n" +
                    "\t[Enter PRINCE, with Attendants]\n" +
                    "\n" +
                    "PRINCE\tRebellious subjects, enemies to peace,\n" +
                    "\tProfaners of this neighbour-stained steel,--\n" +
                    "\tWill they not hear? What, ho! you men, you beasts,\n" +
                    "\tThat quench the fire of your pernicious rage\n" +
                    "\tWith purple fountains issuing from your veins,\n" +
                    "\tOn pain of torture, from those bloody hands\n" +
                    "\tThrow your mistemper'd weapons to the ground,\n" +
                    "\tAnd hear the sentence of your moved prince.\n" +
                    "\tThree civil brawls, bred of an airy word,\n" +
                    "\tBy thee, old Capulet, and Montague,\n" +
                    "\tHave thrice disturb'd the quiet of our streets,\n" +
                    "\tAnd made Verona's ancient citizens\n" +
                    "\tCast by their grave beseeming ornaments,\n" +
                    "\tTo wield old partisans, in hands as old,\n" +
                    "\tCanker'd with peace, to part your canker'd hate:\n" +
                    "\tIf ever you disturb our streets again,\n" +
                    "\tYour lives shall pay the forfeit of the peace.\n" +
                    "\tFor this time, all the rest depart away:\n" +
                    "\tYou Capulet; shall go along with me:\n" +
                    "\tAnd, Montague, come you this afternoon,\n" +
                    "\tTo know our further pleasure in this case,\n" +
                    "\tTo old Free-town, our common judgment-place.\n" +
                    "\tOnce more, on pain of death, all men depart.\n" +
                    "\n" +
                    "\t[Exeunt all but MONTAGUE, LADY MONTAGUE, and BENVOLIO]\n" +
                    "\n" +
                    "MONTAGUE\tWho set this ancient quarrel new abroach?\n" +
                    "\tSpeak, nephew, were you by when it began?\n" +
                    "\n" +
                    "BENVOLIO\tHere were the servants of your adversary,\n" +
                    "\tAnd yours, close fighting ere I did approach:\n" +
                    "\tI drew to part them: in the instant came\n" +
                    "\tThe fiery Tybalt, with his sword prepared,\n" +
                    "\tWhich, as he breathed defiance to my ears,\n" +
                    "\tHe swung about his head and cut the winds,\n" +
                    "\tWho nothing hurt withal hiss'd him in scorn:\n" +
                    "\tWhile we were interchanging thrusts and blows,\n" +
                    "\tCame more and more and fought on part and part,\n" +
                    "\tTill the prince came, who parted either part.\n" +
                    "\n" +
                    "LADY MONTAGUE\tO, where is Romeo? saw you him to-day?\n" +
                    "\tRight glad I am he was not at this fray.\n" +
                    "\n" +
                    "BENVOLIO\tMadam, an hour before the worshipp'd sun\n" +
                    "\tPeer'd forth the golden window of the east,\n" +
                    "\tA troubled mind drave me to walk abroad;\n" +
                    "\tWhere, underneath the grove of sycamore\n" +
                    "\tThat westward rooteth from the city's side,\n" +
                    "\tSo early walking did I see your son:\n" +
                    "\tTowards him I made, but he was ware of me\n" +
                    "\tAnd stole into the covert of the wood:\n" +
                    "\tI, measuring his affections by my own,\n" +
                    "\tThat most are busied when they're most alone,\n" +
                    "\tPursued my humour not pursuing his,\n" +
                    "\tAnd gladly shunn'd who gladly fled from me.\n" +
                    "\n" +
                    "MONTAGUE\tMany a morning hath he there been seen,\n" +
                    "\tWith tears augmenting the fresh morning dew.\n" +
                    "\tAdding to clouds more clouds with his deep sighs;\n" +
                    "\tBut all so soon as the all-cheering sun\n" +
                    "\tShould in the furthest east begin to draw\n" +
                    "\tThe shady curtains from Aurora's bed,\n" +
                    "\tAway from the light steals home my heavy son,\n" +
                    "\tAnd private in his chamber pens himself,\n" +
                    "\tShuts up his windows, locks far daylight out\n" +
                    "\tAnd makes himself an artificial night:\n" +
                    "\tBlack and portentous must this humour prove,\n" +
                    "\tUnless good counsel may the cause remove.\n" +
                    "\n" +
                    "BENVOLIO\tMy noble uncle, do you know the cause?\n" +
                    "\n" +
                    "MONTAGUE\tI neither know it nor can learn of him.\n" +
                    "\n" +
                    "BENVOLIO\tHave you importuned him by any means?\n" +
                    "\n" +
                    "MONTAGUE\tBoth by myself and many other friends:\n" +
                    "\tBut he, his own affections' counsellor,\n" +
                    "\tIs to himself--I will not say how true--\n" +
                    "\tBut to himself so secret and so close,\n" +
                    "\tSo far from sounding and discovery,\n" +
                    "\tAs is the bud bit with an envious worm,\n" +
                    "\tEre he can spread his sweet leaves to the air,\n" +
                    "\tOr dedicate his beauty to the sun.\n" +
                    "\tCould we but learn from whence his sorrows grow.\n" +
                    "\tWe would as willingly give cure as know.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "BENVOLIO\tSee, where he comes: so please you, step aside;\n" +
                    "\tI'll know his grievance, or be much denied.\n" +
                    "\n" +
                    "MONTAGUE\tI would thou wert so happy by thy stay,\n" +
                    "\tTo hear true shrift. Come, madam, let's away.\n" +
                    "\n" +
                    "\t[Exeunt MONTAGUE and LADY MONTAGUE]\n" +
                    "\n" +
                    "BENVOLIO\tGood-morrow, cousin.\n" +
                    "\n" +
                    "ROMEO\tIs the day so young?\n" +
                    "\n" +
                    "BENVOLIO\tBut new struck nine.\n" +
                    "\n" +
                    "ROMEO\tAy me! sad hours seem long.\n" +
                    "\tWas that my father that went hence so fast?\n" +
                    "\n" +
                    "BENVOLIO\tIt was. What sadness lengthens Romeo's hours?\n" +
                    "\n" +
                    "ROMEO\tNot having that, which, having, makes them short.\n" +
                    "\n" +
                    "BENVOLIO\tIn love?\n" +
                    "\n" +
                    "ROMEO\tOut--\n" +
                    "\n" +
                    "BENVOLIO\tOf love?\n" +
                    "\n" +
                    "ROMEO\tOut of her favour, where I am in love.\n" +
                    "\n" +
                    "BENVOLIO\tAlas, that love, so gentle in his view,\n" +
                    "\tShould be so tyrannous and rough in proof!\n" +
                    "\n" +
                    "ROMEO\tAlas, that love, whose view is muffled still,\n" +
                    "\tShould, without eyes, see pathways to his will!\n" +
                    "\tWhere shall we dine? O me! What fray was here?\n" +
                    "\tYet tell me not, for I have heard it all.\n" +
                    "\tHere's much to do with hate, but more with love.\n" +
                    "\tWhy, then, O brawling love! O loving hate!\n" +
                    "\tO any thing, of nothing first create!\n" +
                    "\tO heavy lightness! serious vanity!\n" +
                    "\tMis-shapen chaos of well-seeming forms!\n" +
                    "\tFeather of lead, bright smoke, cold fire,\n" +
                    "\tsick health!\n" +
                    "\tStill-waking sleep, that is not what it is!\n" +
                    "\tThis love feel I, that feel no love in this.\n" +
                    "\tDost thou not laugh?\n" +
                    "\n" +
                    "BENVOLIO\tNo, coz, I rather weep.\n" +
                    "\n" +
                    "ROMEO\tGood heart, at what?\n" +
                    "\n" +
                    "BENVOLIO\tAt thy good heart's oppression.\n" +
                    "\n" +
                    "ROMEO\tWhy, such is love's transgression.\n" +
                    "\tGriefs of mine own lie heavy in my breast,\n" +
                    "\tWhich thou wilt propagate, to have it prest\n" +
                    "\tWith more of thine: this love that thou hast shown\n" +
                    "\tDoth add more grief to too much of mine own.\n" +
                    "\tLove is a smoke raised with the fume of sighs;\n" +
                    "\tBeing purged, a fire sparkling in lovers' eyes;\n" +
                    "\tBeing vex'd a sea nourish'd with lovers' tears:\n" +
                    "\tWhat is it else? a madness most discreet,\n" +
                    "\tA choking gall and a preserving sweet.\n" +
                    "\tFarewell, my coz.\n" +
                    "\n" +
                    "BENVOLIO\t                  Soft! I will go along;\n" +
                    "\tAn if you leave me so, you do me wrong.\n" +
                    "\n" +
                    "ROMEO\tTut, I have lost myself; I am not here;\n" +
                    "\tThis is not Romeo, he's some other where.\n" +
                    "\n" +
                    "BENVOLIO\tTell me in sadness, who is that you love.\n" +
                    "\n" +
                    "ROMEO\tWhat, shall I groan and tell thee?\n" +
                    "\n" +
                    "BENVOLIO\tGroan! why, no.\n" +
                    "\tBut sadly tell me who.\n" +
                    "\n" +
                    "ROMEO\tBid a sick man in sadness make his will:\n" +
                    "\tAh, word ill urged to one that is so ill!\n" +
                    "\tIn sadness, cousin, I do love a woman.\n" +
                    "\n" +
                    "BENVOLIO\tI aim'd so near, when I supposed you loved.\n" +
                    "\n" +
                    "ROMEO\tA right good mark-man! And she's fair I love.\n" +
                    "\n" +
                    "BENVOLIO\tA right fair mark, fair coz, is soonest hit.\n" +
                    "\n" +
                    "ROMEO\tWell, in that hit you miss: she'll not be hit\n" +
                    "\tWith Cupid's arrow; she hath Dian's wit;\n" +
                    "\tAnd, in strong proof of chastity well arm'd,\n" +
                    "\tFrom love's weak childish bow she lives unharm'd.\n" +
                    "\tShe will not stay the siege of loving terms,\n" +
                    "\tNor bide the encounter of assailing eyes,\n" +
                    "\tNor ope her lap to saint-seducing gold:\n" +
                    "\tO, she is rich in beauty, only poor,\n" +
                    "\tThat when she dies with beauty dies her store.\n" +
                    "\n" +
                    "BENVOLIO\tThen she hath sworn that she will still live chaste?\n" +
                    "\n" +
                    "ROMEO\tShe hath, and in that sparing makes huge waste,\n" +
                    "\tFor beauty starved with her severity\n" +
                    "\tCuts beauty off from all posterity.\n" +
                    "\tShe is too fair, too wise, wisely too fair,\n" +
                    "\tTo merit bliss by making me despair:\n" +
                    "\tShe hath forsworn to love, and in that vow\n" +
                    "\tDo I live dead that live to tell it now.\n" +
                    "\n" +
                    "BENVOLIO\tBe ruled by me, forget to think of her.\n" +
                    "\n" +
                    "ROMEO\tO, teach me how I should forget to think.\n" +
                    "\n" +
                    "BENVOLIO\tBy giving liberty unto thine eyes;\n" +
                    "\tExamine other beauties.\n" +
                    "\n" +
                    "ROMEO\t'Tis the way\n" +
                    "\tTo call hers exquisite, in question more:\n" +
                    "\tThese happy masks that kiss fair ladies' brows\n" +
                    "\tBeing black put us in mind they hide the fair;\n" +
                    "\tHe that is strucken blind cannot forget\n" +
                    "\tThe precious treasure of his eyesight lost:\n" +
                    "\tShow me a mistress that is passing fair,\n" +
                    "\tWhat doth her beauty serve, but as a note\n" +
                    "\tWhere I may read who pass'd that passing fair?\n" +
                    "\tFarewell: thou canst not teach me to forget.\n" +
                    "\n" +
                    "BENVOLIO\tI'll pay that doctrine, or else die in debt.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT I\n" +
                    "\n" +
                    "SCENE II\tA street.\n" +
                    "\n" +
                    "\t[Enter CAPULET, PARIS, and Servant]\n" +
                    "\n" +
                    "CAPULET\tBut Montague is bound as well as I,\n" +
                    "\tIn penalty alike; and 'tis not hard, I think,\n" +
                    "\tFor men so old as we to keep the peace.\n" +
                    "\n" +
                    "PARIS\tOf honourable reckoning are you both;\n" +
                    "\tAnd pity 'tis you lived at odds so long.\n" +
                    "\tBut now, my lord, what say you to my suit?\n" +
                    "\n" +
                    "CAPULET\tBut saying o'er what I have said before:\n" +
                    "\tMy child is yet a stranger in the world;\n" +
                    "\tShe hath not seen the change of fourteen years,\n" +
                    "\tLet two more summers wither in their pride,\n" +
                    "\tEre we may think her ripe to be a bride.\n" +
                    "\n" +
                    "PARIS\tYounger than she are happy mothers made.\n" +
                    "\n" +
                    "CAPULET\tAnd too soon marr'd are those so early made.\n" +
                    "\tThe earth hath swallow'd all my hopes but she,\n" +
                    "\tShe is the hopeful lady of my earth:\n" +
                    "\tBut woo her, gentle Paris, get her heart,\n" +
                    "\tMy will to her consent is but a part;\n" +
                    "\tAn she agree, within her scope of choice\n" +
                    "\tLies my consent and fair according voice.\n" +
                    "\tThis night I hold an old accustom'd feast,\n" +
                    "\tWhereto I have invited many a guest,\n" +
                    "\tSuch as I love; and you, among the store,\n" +
                    "\tOne more, most welcome, makes my number more.\n" +
                    "\tAt my poor house look to behold this night\n" +
                    "\tEarth-treading stars that make dark heaven light:\n" +
                    "\tSuch comfort as do lusty young men feel\n" +
                    "\tWhen well-apparell'd April on the heel\n" +
                    "\tOf limping winter treads, even such delight\n" +
                    "\tAmong fresh female buds shall you this night\n" +
                    "\tInherit at my house; hear all, all see,\n" +
                    "\tAnd like her most whose merit most shall be:\n" +
                    "\tWhich on more view, of many mine being one\n" +
                    "\tMay stand in number, though in reckoning none,\n" +
                    "\tCome, go with me.\n" +
                    "\n" +
                    "\t[To Servant, giving a paper]\n" +
                    "\n" +
                    "\tGo, sirrah, trudge about\n" +
                    "\tThrough fair Verona; find those persons out\n" +
                    "\tWhose names are written there, and to them say,\n" +
                    "\tMy house and welcome on their pleasure stay.\n" +
                    "\n" +
                    "\t[Exeunt CAPULET and PARIS]\n" +
                    "\n" +
                    "Servant\tFind them out whose names are written here! It is\n" +
                    "\twritten, that the shoemaker should meddle with his\n" +
                    "\tyard, and the tailor with his last, the fisher with\n" +
                    "\this pencil, and the painter with his nets; but I am\n" +
                    "\tsent to find those persons whose names are here\n" +
                    "\twrit, and can never find what names the writing\n" +
                    "\tperson hath here writ. I must to the learned.--In good time.\n" +
                    "\n" +
                    "\t[Enter BENVOLIO and ROMEO]\n" +
                    "\n" +
                    "BENVOLIO\tTut, man, one fire burns out another's burning,\n" +
                    "\tOne pain is lessen'd by another's anguish;\n" +
                    "\tTurn giddy, and be holp by backward turning;\n" +
                    "\tOne desperate grief cures with another's languish:\n" +
                    "\tTake thou some new infection to thy eye,\n" +
                    "\tAnd the rank poison of the old will die.\n" +
                    "\n" +
                    "ROMEO\tYour plaintain-leaf is excellent for that.\n" +
                    "\n" +
                    "BENVOLIO\tFor what, I pray thee?\n" +
                    "\n" +
                    "ROMEO\tFor your broken shin.\n" +
                    "\n" +
                    "BENVOLIO\tWhy, Romeo, art thou mad?\n" +
                    "\n" +
                    "ROMEO\tNot mad, but bound more than a mad-man is;\n" +
                    "\tShut up in prison, kept without my food,\n" +
                    "\tWhipp'd and tormented and--God-den, good fellow.\n" +
                    "\n" +
                    "Servant\tGod gi' god-den. I pray, sir, can you read?\n" +
                    "\n" +
                    "ROMEO\tAy, mine own fortune in my misery.\n" +
                    "\n" +
                    "Servant\tPerhaps you have learned it without book: but, I\n" +
                    "\tpray, can you read any thing you see?\n" +
                    "\n" +
                    "ROMEO\tAy, if I know the letters and the language.\n" +
                    "\n" +
                    "Servant\tYe say honestly: rest you merry!\n" +
                    "\n" +
                    "ROMEO\tStay, fellow; I can read.\n" +
                    "\n" +
                    "\t[Reads]\n" +
                    "\n" +
                    "\t'Signior Martino and his wife and daughters;\n" +
                    "\tCounty Anselme and his beauteous sisters; the lady\n" +
                    "\twidow of Vitravio; Signior Placentio and his lovely\n" +
                    "\tnieces; Mercutio and his brother Valentine; mine\n" +
                    "\tuncle Capulet, his wife and daughters; my fair niece\n" +
                    "\tRosaline; Livia; Signior Valentio and his cousin\n" +
                    "\tTybalt, Lucio and the lively Helena.' A fair\n" +
                    "\tassembly: whither should they come?\n" +
                    "\n" +
                    "Servant\tUp.\n" +
                    "\n" +
                    "ROMEO\tWhither?\n" +
                    "\n" +
                    "Servant\tTo supper; to our house.\n" +
                    "\n" +
                    "ROMEO\tWhose house?\n" +
                    "\n" +
                    "Servant\tMy master's.\n" +
                    "\n" +
                    "ROMEO\tIndeed, I should have ask'd you that before.\n" +
                    "\n" +
                    "Servant\tNow I'll tell you without asking: my master is the\n" +
                    "\tgreat rich Capulet; and if you be not of the house\n" +
                    "\tof Montagues, I pray, come and crush a cup of wine.\n" +
                    "\tRest you merry!\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "BENVOLIO\tAt this same ancient feast of Capulet's\n" +
                    "\tSups the fair Rosaline whom thou so lovest,\n" +
                    "\tWith all the admired beauties of Verona:\n" +
                    "\tGo thither; and, with unattainted eye,\n" +
                    "\tCompare her face with some that I shall show,\n" +
                    "\tAnd I will make thee think thy swan a crow.\n" +
                    "\n" +
                    "ROMEO\tWhen the devout religion of mine eye\n" +
                    "\tMaintains such falsehood, then turn tears to fires;\n" +
                    "\tAnd these, who often drown'd could never die,\n" +
                    "\tTransparent heretics, be burnt for liars!\n" +
                    "\tOne fairer than my love! the all-seeing sun\n" +
                    "\tNe'er saw her match since first the world begun.\n" +
                    "\n" +
                    "BENVOLIO\tTut, you saw her fair, none else being by,\n" +
                    "\tHerself poised with herself in either eye:\n" +
                    "\tBut in that crystal scales let there be weigh'd\n" +
                    "\tYour lady's love against some other maid\n" +
                    "\tThat I will show you shining at this feast,\n" +
                    "\tAnd she shall scant show well that now shows best.\n" +
                    "\n" +
                    "ROMEO\tI'll go along, no such sight to be shown,\n" +
                    "\tBut to rejoice in splendor of mine own.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT I\n" +
                    "\n" +
                    "SCENE III\tA room in Capulet's house.\n" +
                    "\n" +
                    "\t[Enter LADY CAPULET and Nurse]\n" +
                    "\n" +
                    "LADY CAPULET\tNurse, where's my daughter? call her forth to me.\n" +
                    "\n" +
                    "Nurse\tNow, by my maidenhead, at twelve year old,\n" +
                    "\tI bade her come. What, lamb! what, ladybird!\n" +
                    "\tGod forbid! Where's this girl? What, Juliet!\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "JULIET\tHow now! who calls?\n" +
                    "\n" +
                    "Nurse\tYour mother.\n" +
                    "\n" +
                    "JULIET\tMadam, I am here.\n" +
                    "\tWhat is your will?\n" +
                    "\n" +
                    "LADY CAPULET\tThis is the matter:--Nurse, give leave awhile,\n" +
                    "\tWe must talk in secret:--nurse, come back again;\n" +
                    "\tI have remember'd me, thou's hear our counsel.\n" +
                    "\tThou know'st my daughter's of a pretty age.\n" +
                    "\n" +
                    "Nurse\tFaith, I can tell her age unto an hour.\n" +
                    "\n" +
                    "LADY CAPULET\tShe's not fourteen.\n" +
                    "\n" +
                    "Nurse\tI'll lay fourteen of my teeth,--\n" +
                    "\tAnd yet, to my teeth be it spoken, I have but four--\n" +
                    "\tShe is not fourteen. How long is it now\n" +
                    "\tTo Lammas-tide?\n" +
                    "\n" +
                    "LADY CAPULET\t                  A fortnight and odd days.\n" +
                    "\n" +
                    "Nurse\tEven or odd, of all days in the year,\n" +
                    "\tCome Lammas-eve at night shall she be fourteen.\n" +
                    "\tSusan and she--God rest all Christian souls!--\n" +
                    "\tWere of an age: well, Susan is with God;\n" +
                    "\tShe was too good for me: but, as I said,\n" +
                    "\tOn Lammas-eve at night shall she be fourteen;\n" +
                    "\tThat shall she, marry; I remember it well.\n" +
                    "\t'Tis since the earthquake now eleven years;\n" +
                    "\tAnd she was wean'd,--I never shall forget it,--\n" +
                    "\tOf all the days of the year, upon that day:\n" +
                    "\tFor I had then laid wormwood to my dug,\n" +
                    "\tSitting in the sun under the dove-house wall;\n" +
                    "\tMy lord and you were then at Mantua:--\n" +
                    "\tNay, I do bear a brain:--but, as I said,\n" +
                    "\tWhen it did taste the wormwood on the nipple\n" +
                    "\tOf my dug and felt it bitter, pretty fool,\n" +
                    "\tTo see it tetchy and fall out with the dug!\n" +
                    "\tShake quoth the dove-house: 'twas no need, I trow,\n" +
                    "\tTo bid me trudge:\n" +
                    "\tAnd since that time it is eleven years;\n" +
                    "\tFor then she could stand alone; nay, by the rood,\n" +
                    "\tShe could have run and waddled all about;\n" +
                    "\tFor even the day before, she broke her brow:\n" +
                    "\tAnd then my husband--God be with his soul!\n" +
                    "\tA' was a merry man--took up the child:\n" +
                    "\t'Yea,' quoth he, 'dost thou fall upon thy face?\n" +
                    "\tThou wilt fall backward when thou hast more wit;\n" +
                    "\tWilt thou not, Jule?' and, by my holidame,\n" +
                    "\tThe pretty wretch left crying and said 'Ay.'\n" +
                    "\tTo see, now, how a jest shall come about!\n" +
                    "\tI warrant, an I should live a thousand years,\n" +
                    "\tI never should forget it: 'Wilt thou not, Jule?' quoth he;\n" +
                    "\tAnd, pretty fool, it stinted and said 'Ay.'\n" +
                    "\n" +
                    "LADY CAPULET\tEnough of this; I pray thee, hold thy peace.\n" +
                    "\n" +
                    "Nurse\tYes, madam: yet I cannot choose but laugh,\n" +
                    "\tTo think it should leave crying and say 'Ay.'\n" +
                    "\tAnd yet, I warrant, it had upon its brow\n" +
                    "\tA bump as big as a young cockerel's stone;\n" +
                    "\tA parlous knock; and it cried bitterly:\n" +
                    "\t'Yea,' quoth my husband,'fall'st upon thy face?\n" +
                    "\tThou wilt fall backward when thou comest to age;\n" +
                    "\tWilt thou not, Jule?' it stinted and said 'Ay.'\n" +
                    "\n" +
                    "JULIET\tAnd stint thou too, I pray thee, nurse, say I.\n" +
                    "\n" +
                    "Nurse\tPeace, I have done. God mark thee to his grace!\n" +
                    "\tThou wast the prettiest babe that e'er I nursed:\n" +
                    "\tAn I might live to see thee married once,\n" +
                    "\tI have my wish.\n" +
                    "\n" +
                    "LADY CAPULET\tMarry, that 'marry' is the very theme\n" +
                    "\tI came to talk of. Tell me, daughter Juliet,\n" +
                    "\tHow stands your disposition to be married?\n" +
                    "\n" +
                    "JULIET\tIt is an honour that I dream not of.\n" +
                    "\n" +
                    "Nurse\tAn honour! were not I thine only nurse,\n" +
                    "\tI would say thou hadst suck'd wisdom from thy teat.\n" +
                    "\n" +
                    "LADY CAPULET\tWell, think of marriage now; younger than you,\n" +
                    "\tHere in Verona, ladies of esteem,\n" +
                    "\tAre made already mothers: by my count,\n" +
                    "\tI was your mother much upon these years\n" +
                    "\tThat you are now a maid. Thus then in brief:\n" +
                    "\tThe valiant Paris seeks you for his love.\n" +
                    "\n" +
                    "Nurse\tA man, young lady! lady, such a man\n" +
                    "\tAs all the world--why, he's a man of wax.\n" +
                    "\n" +
                    "LADY CAPULET\tVerona's summer hath not such a flower.\n" +
                    "\n" +
                    "Nurse\tNay, he's a flower; in faith, a very flower.\n" +
                    "\n" +
                    "LADY CAPULET\tWhat say you? can you love the gentleman?\n" +
                    "\tThis night you shall behold him at our feast;\n" +
                    "\tRead o'er the volume of young Paris' face,\n" +
                    "\tAnd find delight writ there with beauty's pen;\n" +
                    "\tExamine every married lineament,\n" +
                    "\tAnd see how one another lends content\n" +
                    "\tAnd what obscured in this fair volume lies\n" +
                    "\tFind written in the margent of his eyes.\n" +
                    "\tThis precious book of love, this unbound lover,\n" +
                    "\tTo beautify him, only lacks a cover:\n" +
                    "\tThe fish lives in the sea, and 'tis much pride\n" +
                    "\tFor fair without the fair within to hide:\n" +
                    "\tThat book in many's eyes doth share the glory,\n" +
                    "\tThat in gold clasps locks in the golden story;\n" +
                    "\tSo shall you share all that he doth possess,\n" +
                    "\tBy having him, making yourself no less.\n" +
                    "\n" +
                    "Nurse\tNo less! nay, bigger; women grow by men.\n" +
                    "\n" +
                    "LADY CAPULET\tSpeak briefly, can you like of Paris' love?\n" +
                    "\n" +
                    "JULIET\tI'll look to like, if looking liking move:\n" +
                    "\tBut no more deep will I endart mine eye\n" +
                    "\tThan your consent gives strength to make it fly.\n" +
                    "\n" +
                    "\t[Enter a Servant]\n" +
                    "\n" +
                    "Servant\tMadam, the guests are come, supper served up, you\n" +
                    "\tcalled, my young lady asked for, the nurse cursed in\n" +
                    "\tthe pantry, and every thing in extremity. I must\n" +
                    "\thence to wait; I beseech you, follow straight.\n" +
                    "\n" +
                    "LADY CAPULET\tWe follow thee.\n" +
                    "\n" +
                    "\t[Exit Servant]\n" +
                    "\n" +
                    "\tJuliet, the county stays.\n" +
                    "\n" +
                    "Nurse\tGo, girl, seek happy nights to happy days.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT I\n" +
                    "\n" +
                    "SCENE IV\tA street.\n" +
                    "\n" +
                    "\t[Enter ROMEO, MERCUTIO, BENVOLIO, with five or six\n" +
                    "\tMaskers, Torch-bearers, and others]\n" +
                    "\n" +
                    "ROMEO\tWhat, shall this speech be spoke for our excuse?\n" +
                    "\tOr shall we on without a apology?\n" +
                    "\n" +
                    "BENVOLIO\tThe date is out of such prolixity:\n" +
                    "\tWe'll have no Cupid hoodwink'd with a scarf,\n" +
                    "\tBearing a Tartar's painted bow of lath,\n" +
                    "\tScaring the ladies like a crow-keeper;\n" +
                    "\tNor no without-book prologue, faintly spoke\n" +
                    "\tAfter the prompter, for our entrance:\n" +
                    "\tBut let them measure us by what they will;\n" +
                    "\tWe'll measure them a measure, and be gone.\n" +
                    "\n" +
                    "ROMEO\tGive me a torch: I am not for this ambling;\n" +
                    "\tBeing but heavy, I will bear the light.\n" +
                    "\n" +
                    "MERCUTIO\tNay, gentle Romeo, we must have you dance.\n" +
                    "\n" +
                    "ROMEO\tNot I, believe me: you have dancing shoes\n" +
                    "\tWith nimble soles: I have a soul of lead\n" +
                    "\tSo stakes me to the ground I cannot move.\n" +
                    "\n" +
                    "MERCUTIO\tYou are a lover; borrow Cupid's wings,\n" +
                    "\tAnd soar with them above a common bound.\n" +
                    "\n" +
                    "ROMEO\tI am too sore enpierced with his shaft\n" +
                    "\tTo soar with his light feathers, and so bound,\n" +
                    "\tI cannot bound a pitch above dull woe:\n" +
                    "\tUnder love's heavy burden do I sink.\n" +
                    "\n" +
                    "MERCUTIO\tAnd, to sink in it, should you burden love;\n" +
                    "\tToo great oppression for a tender thing.\n" +
                    "\n" +
                    "ROMEO\tIs love a tender thing? it is too rough,\n" +
                    "\tToo rude, too boisterous, and it pricks like thorn.\n" +
                    "\n" +
                    "MERCUTIO\tIf love be rough with you, be rough with love;\n" +
                    "\tPrick love for pricking, and you beat love down.\n" +
                    "\tGive me a case to put my visage in:\n" +
                    "\tA visor for a visor! what care I\n" +
                    "\tWhat curious eye doth quote deformities?\n" +
                    "\tHere are the beetle brows shall blush for me.\n" +
                    "\n" +
                    "BENVOLIO\tCome, knock and enter; and no sooner in,\n" +
                    "\tBut every man betake him to his legs.\n" +
                    "\n" +
                    "ROMEO\tA torch for me: let wantons light of heart\n" +
                    "\tTickle the senseless rushes with their heels,\n" +
                    "\tFor I am proverb'd with a grandsire phrase;\n" +
                    "\tI'll be a candle-holder, and look on.\n" +
                    "\tThe game was ne'er so fair, and I am done.\n" +
                    "\n" +
                    "MERCUTIO\tTut, dun's the mouse, the constable's own word:\n" +
                    "\tIf thou art dun, we'll draw thee from the mire\n" +
                    "\tOf this sir-reverence love, wherein thou stick'st\n" +
                    "\tUp to the ears. Come, we burn daylight, ho!\n" +
                    "\n" +
                    "ROMEO\tNay, that's not so.\n" +
                    "\n" +
                    "MERCUTIO\tI mean, sir, in delay\n" +
                    "\tWe waste our lights in vain, like lamps by day.\n" +
                    "\tTake our good meaning, for our judgment sits\n" +
                    "\tFive times in that ere once in our five wits.\n" +
                    "\n" +
                    "ROMEO\tAnd we mean well in going to this mask;\n" +
                    "\tBut 'tis no wit to go.\n" +
                    "\n" +
                    "MERCUTIO\tWhy, may one ask?\n" +
                    "\n" +
                    "ROMEO\tI dream'd a dream to-night.\n" +
                    "\n" +
                    "MERCUTIO\tAnd so did I.\n" +
                    "\n" +
                    "ROMEO\tWell, what was yours?\n" +
                    "\n" +
                    "MERCUTIO\tThat dreamers often lie.\n" +
                    "\n" +
                    "ROMEO\tIn bed asleep, while they do dream things true.\n" +
                    "\n" +
                    "MERCUTIO\tO, then, I see Queen Mab hath been with you.\n" +
                    "\tShe is the fairies' midwife, and she comes\n" +
                    "\tIn shape no bigger than an agate-stone\n" +
                    "\tOn the fore-finger of an alderman,\n" +
                    "\tDrawn with a team of little atomies\n" +
                    "\tAthwart men's noses as they lie asleep;\n" +
                    "\tHer wagon-spokes made of long spiders' legs,\n" +
                    "\tThe cover of the wings of grasshoppers,\n" +
                    "\tThe traces of the smallest spider's web,\n" +
                    "\tThe collars of the moonshine's watery beams,\n" +
                    "\tHer whip of cricket's bone, the lash of film,\n" +
                    "\tHer wagoner a small grey-coated gnat,\n" +
                    "\tNot so big as a round little worm\n" +
                    "\tPrick'd from the lazy finger of a maid;\n" +
                    "\tHer chariot is an empty hazel-nut\n" +
                    "\tMade by the joiner squirrel or old grub,\n" +
                    "\tTime out o' mind the fairies' coachmakers.\n" +
                    "\tAnd in this state she gallops night by night\n" +
                    "\tThrough lovers' brains, and then they dream of love;\n" +
                    "\tO'er courtiers' knees, that dream on court'sies straight,\n" +
                    "\tO'er lawyers' fingers, who straight dream on fees,\n" +
                    "\tO'er ladies ' lips, who straight on kisses dream,\n" +
                    "\tWhich oft the angry Mab with blisters plagues,\n" +
                    "\tBecause their breaths with sweetmeats tainted are:\n" +
                    "\tSometime she gallops o'er a courtier's nose,\n" +
                    "\tAnd then dreams he of smelling out a suit;\n" +
                    "\tAnd sometime comes she with a tithe-pig's tail\n" +
                    "\tTickling a parson's nose as a' lies asleep,\n" +
                    "\tThen dreams, he of another benefice:\n" +
                    "\tSometime she driveth o'er a soldier's neck,\n" +
                    "\tAnd then dreams he of cutting foreign throats,\n" +
                    "\tOf breaches, ambuscadoes, Spanish blades,\n" +
                    "\tOf healths five-fathom deep; and then anon\n" +
                    "\tDrums in his ear, at which he starts and wakes,\n" +
                    "\tAnd being thus frighted swears a prayer or two\n" +
                    "\tAnd sleeps again. This is that very Mab\n" +
                    "\tThat plats the manes of horses in the night,\n" +
                    "\tAnd bakes the elflocks in foul sluttish hairs,\n" +
                    "\tWhich once untangled, much misfortune bodes:\n" +
                    "\tThis is the hag, when maids lie on their backs,\n" +
                    "\tThat presses them and learns them first to bear,\n" +
                    "\tMaking them women of good carriage:\n" +
                    "\tThis is she--\n" +
                    "\n" +
                    "ROMEO\t                  Peace, peace, Mercutio, peace!\n" +
                    "\tThou talk'st of nothing.\n" +
                    "\n" +
                    "MERCUTIO\tTrue, I talk of dreams,\n" +
                    "\tWhich are the children of an idle brain,\n" +
                    "\tBegot of nothing but vain fantasy,\n" +
                    "\tWhich is as thin of substance as the air\n" +
                    "\tAnd more inconstant than the wind, who wooes\n" +
                    "\tEven now the frozen bosom of the north,\n" +
                    "\tAnd, being anger'd, puffs away from thence,\n" +
                    "\tTurning his face to the dew-dropping south.\n" +
                    "\n" +
                    "BENVOLIO\tThis wind, you talk of, blows us from ourselves;\n" +
                    "\tSupper is done, and we shall come too late.\n" +
                    "\n" +
                    "ROMEO\tI fear, too early: for my mind misgives\n" +
                    "\tSome consequence yet hanging in the stars\n" +
                    "\tShall bitterly begin his fearful date\n" +
                    "\tWith this night's revels and expire the term\n" +
                    "\tOf a despised life closed in my breast\n" +
                    "\tBy some vile forfeit of untimely death.\n" +
                    "\tBut He, that hath the steerage of my course,\n" +
                    "\tDirect my sail! On, lusty gentlemen.\n" +
                    "\n" +
                    "BENVOLIO\tStrike, drum.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT I\n" +
                    "\n" +
                    "SCENE V\tA hall in Capulet's house.\n" +
                    "\n" +
                    "\t[Musicians waiting. Enter Servingmen with napkins]\n" +
                    "\n" +
                    "First Servant\tWhere's Potpan, that he helps not to take away? He\n" +
                    "\tshift a trencher? he scrape a trencher!\n" +
                    "\n" +
                    "Second Servant\tWhen good manners shall lie all in one or two men's\n" +
                    "\thands and they unwashed too, 'tis a foul thing.\n" +
                    "\n" +
                    "First Servant\tAway with the joint-stools, remove the\n" +
                    "\tcourt-cupboard, look to the plate. Good thou, save\n" +
                    "\tme a piece of marchpane; and, as thou lovest me, let\n" +
                    "\tthe porter let in Susan Grindstone and Nell.\n" +
                    "\tAntony, and Potpan!\n" +
                    "\n" +
                    "Second Servant\tAy, boy, ready.\n" +
                    "\n" +
                    "First Servant\tYou are looked for and called for, asked for and\n" +
                    "\tsought for, in the great chamber.\n" +
                    "\n" +
                    "Second Servant\tWe cannot be here and there too. Cheerly, boys; be\n" +
                    "\tbrisk awhile, and the longer liver take all.\n" +
                    "\n" +
                    "\t[Enter CAPULET, with JULIET and others of his house,\n" +
                    "\tmeeting the Guests and Maskers]\n" +
                    "\n" +
                    "CAPULET\tWelcome, gentlemen! ladies that have their toes\n" +
                    "\tUnplagued with corns will have a bout with you.\n" +
                    "\tAh ha, my mistresses! which of you all\n" +
                    "\tWill now deny to dance? she that makes dainty,\n" +
                    "\tShe, I'll swear, hath corns; am I come near ye now?\n" +
                    "\tWelcome, gentlemen! I have seen the day\n" +
                    "\tThat I have worn a visor and could tell\n" +
                    "\tA whispering tale in a fair lady's ear,\n" +
                    "\tSuch as would please: 'tis gone, 'tis gone, 'tis gone:\n" +
                    "\tYou are welcome, gentlemen! come, musicians, play.\n" +
                    "\tA hall, a hall! give room! and foot it, girls.\n" +
                    "\n" +
                    "\t[Music plays, and they dance]\n" +
                    "\n" +
                    "\tMore light, you knaves; and turn the tables up,\n" +
                    "\tAnd quench the fire, the room is grown too hot.\n" +
                    "\tAh, sirrah, this unlook'd-for sport comes well.\n" +
                    "\tNay, sit, nay, sit, good cousin Capulet;\n" +
                    "\tFor you and I are past our dancing days:\n" +
                    "\tHow long is't now since last yourself and I\n" +
                    "\tWere in a mask?\n" +
                    "\n" +
                    "Second Capulet\t                  By'r lady, thirty years.\n" +
                    "\n" +
                    "CAPULET\tWhat, man! 'tis not so much, 'tis not so much:\n" +
                    "\t'Tis since the nuptials of Lucentio,\n" +
                    "\tCome pentecost as quickly as it will,\n" +
                    "\tSome five and twenty years; and then we mask'd.\n" +
                    "\n" +
                    "Second Capulet\t'Tis more, 'tis more, his son is elder, sir;\n" +
                    "\tHis son is thirty.\n" +
                    "\n" +
                    "CAPULET\t                  Will you tell me that?\n" +
                    "\tHis son was but a ward two years ago.\n" +
                    "\n" +
                    "ROMEO\t[To a Servingman]  What lady is that, which doth\n" +
                    "\tenrich the hand\n" +
                    "\tOf yonder knight?\n" +
                    "\n" +
                    "Servant\tI know not, sir.\n" +
                    "\n" +
                    "ROMEO\tO, she doth teach the torches to burn bright!\n" +
                    "\tIt seems she hangs upon the cheek of night\n" +
                    "\tLike a rich jewel in an Ethiope's ear;\n" +
                    "\tBeauty too rich for use, for earth too dear!\n" +
                    "\tSo shows a snowy dove trooping with crows,\n" +
                    "\tAs yonder lady o'er her fellows shows.\n" +
                    "\tThe measure done, I'll watch her place of stand,\n" +
                    "\tAnd, touching hers, make blessed my rude hand.\n" +
                    "\tDid my heart love till now? forswear it, sight!\n" +
                    "\tFor I ne'er saw true beauty till this night.\n" +
                    "\n" +
                    "TYBALT\tThis, by his voice, should be a Montague.\n" +
                    "\tFetch me my rapier, boy. What dares the slave\n" +
                    "\tCome hither, cover'd with an antic face,\n" +
                    "\tTo fleer and scorn at our solemnity?\n" +
                    "\tNow, by the stock and honour of my kin,\n" +
                    "\tTo strike him dead, I hold it not a sin.\n" +
                    "\n" +
                    "CAPULET\tWhy, how now, kinsman! wherefore storm you so?\n" +
                    "\n" +
                    "TYBALT\tUncle, this is a Montague, our foe,\n" +
                    "\tA villain that is hither come in spite,\n" +
                    "\tTo scorn at our solemnity this night.\n" +
                    "\n" +
                    "CAPULET\tYoung Romeo is it?\n" +
                    "\n" +
                    "TYBALT\t'Tis he, that villain Romeo.\n" +
                    "\n" +
                    "CAPULET\tContent thee, gentle coz, let him alone;\n" +
                    "\tHe bears him like a portly gentleman;\n" +
                    "\tAnd, to say truth, Verona brags of him\n" +
                    "\tTo be a virtuous and well-govern'd youth:\n" +
                    "\tI would not for the wealth of all the town\n" +
                    "\tHere in my house do him disparagement:\n" +
                    "\tTherefore be patient, take no note of him:\n" +
                    "\tIt is my will, the which if thou respect,\n" +
                    "\tShow a fair presence and put off these frowns,\n" +
                    "\tAnd ill-beseeming semblance for a feast.\n" +
                    "\n" +
                    "TYBALT\tIt fits, when such a villain is a guest:\n" +
                    "\tI'll not endure him.\n" +
                    "\n" +
                    "CAPULET\tHe shall be endured:\n" +
                    "\tWhat, goodman boy! I say, he shall: go to;\n" +
                    "\tAm I the master here, or you? go to.\n" +
                    "\tYou'll not endure him! God shall mend my soul!\n" +
                    "\tYou'll make a mutiny among my guests!\n" +
                    "\tYou will set cock-a-hoop! you'll be the man!\n" +
                    "\n" +
                    "TYBALT\tWhy, uncle, 'tis a shame.\n" +
                    "\n" +
                    "CAPULET\tGo to, go to;\n" +
                    "\tYou are a saucy boy: is't so, indeed?\n" +
                    "\tThis trick may chance to scathe you, I know what:\n" +
                    "\tYou must contrary me! marry, 'tis time.\n" +
                    "\tWell said, my hearts! You are a princox; go:\n" +
                    "\tBe quiet, or--More light, more light! For shame!\n" +
                    "\tI'll make you quiet. What, cheerly, my hearts!\n" +
                    "\n" +
                    "TYBALT\tPatience perforce with wilful choler meeting\n" +
                    "\tMakes my flesh tremble in their different greeting.\n" +
                    "\tI will withdraw: but this intrusion shall\n" +
                    "\tNow seeming sweet convert to bitter gall.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "ROMEO\t[To JULIET]  If I profane with my unworthiest hand\n" +
                    "\tThis holy shrine, the gentle fine is this:\n" +
                    "\tMy lips, two blushing pilgrims, ready stand\n" +
                    "\tTo smooth that rough touch with a tender kiss.\n" +
                    "\n" +
                    "JULIET\tGood pilgrim, you do wrong your hand too much,\n" +
                    "\tWhich mannerly devotion shows in this;\n" +
                    "\tFor saints have hands that pilgrims' hands do touch,\n" +
                    "\tAnd palm to palm is holy palmers' kiss.\n" +
                    "\n" +
                    "ROMEO\tHave not saints lips, and holy palmers too?\n" +
                    "\n" +
                    "JULIET\tAy, pilgrim, lips that they must use in prayer.\n" +
                    "\n" +
                    "ROMEO\tO, then, dear saint, let lips do what hands do;\n" +
                    "\tThey pray, grant thou, lest faith turn to despair.\n" +
                    "\n" +
                    "JULIET\tSaints do not move, though grant for prayers' sake.\n" +
                    "\n" +
                    "ROMEO\tThen move not, while my prayer's effect I take.\n" +
                    "\tThus from my lips, by yours, my sin is purged.\n" +
                    "\n" +
                    "JULIET\tThen have my lips the sin that they have took.\n" +
                    "\n" +
                    "ROMEO\tSin from thy lips? O trespass sweetly urged!\n" +
                    "\tGive me my sin again.\n" +
                    "\n" +
                    "JULIET\tYou kiss by the book.\n" +
                    "\n" +
                    "Nurse\tMadam, your mother craves a word with you.\n" +
                    "\n" +
                    "ROMEO\tWhat is her mother?\n" +
                    "\n" +
                    "Nurse\tMarry, bachelor,\n" +
                    "\tHer mother is the lady of the house,\n" +
                    "\tAnd a good lady, and a wise and virtuous\n" +
                    "\tI nursed her daughter, that you talk'd withal;\n" +
                    "\tI tell you, he that can lay hold of her\n" +
                    "\tShall have the chinks.\n" +
                    "\n" +
                    "ROMEO\tIs she a Capulet?\n" +
                    "\tO dear account! my life is my foe's debt.\n" +
                    "\n" +
                    "BENVOLIO\tAway, begone; the sport is at the best.\n" +
                    "\n" +
                    "ROMEO\tAy, so I fear; the more is my unrest.\n" +
                    "\n" +
                    "CAPULET\tNay, gentlemen, prepare not to be gone;\n" +
                    "\tWe have a trifling foolish banquet towards.\n" +
                    "\tIs it e'en so? why, then, I thank you all\n" +
                    "\tI thank you, honest gentlemen; good night.\n" +
                    "\tMore torches here! Come on then, let's to bed.\n" +
                    "\tAh, sirrah, by my fay, it waxes late:\n" +
                    "\tI'll to my rest.\n" +
                    "\n" +
                    "\t[Exeunt all but JULIET and Nurse]\n" +
                    "\n" +
                    "JULIET\tCome hither, nurse. What is yond gentleman?\n" +
                    "\n" +
                    "Nurse\tThe son and heir of old Tiberio.\n" +
                    "\n" +
                    "JULIET\tWhat's he that now is going out of door?\n" +
                    "\n" +
                    "Nurse\tMarry, that, I think, be young Petrucio.\n" +
                    "\n" +
                    "JULIET\tWhat's he that follows there, that would not dance?\n" +
                    "\n" +
                    "Nurse\tI know not.\n" +
                    "\n" +
                    "JULIET\tGo ask his name: if he be married.\n" +
                    "\tMy grave is like to be my wedding bed.\n" +
                    "\n" +
                    "Nurse\tHis name is Romeo, and a Montague;\n" +
                    "\tThe only son of your great enemy.\n" +
                    "\n" +
                    "JULIET\tMy only love sprung from my only hate!\n" +
                    "\tToo early seen unknown, and known too late!\n" +
                    "\tProdigious birth of love it is to me,\n" +
                    "\tThat I must love a loathed enemy.\n" +
                    "\n" +
                    "Nurse\tWhat's this? what's this?\n" +
                    "\n" +
                    "JULIET\tA rhyme I learn'd even now\n" +
                    "\tOf one I danced withal.\n" +
                    "\n" +
                    "\t[One calls within 'Juliet.']\n" +
                    "\n" +
                    "Nurse\tAnon, anon!\n" +
                    "\tCome, let's away; the strangers all are gone.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "\tPROLOGUE\n" +
                    "\n" +
                    "\t[Enter Chorus]\n" +
                    "\n" +
                    "Chorus\tNow old desire doth in his death-bed lie,\n" +
                    "\tAnd young affection gapes to be his heir;\n" +
                    "\tThat fair for which love groan'd for and would die,\n" +
                    "\tWith tender Juliet match'd, is now not fair.\n" +
                    "\tNow Romeo is beloved and loves again,\n" +
                    "\tAlike betwitched by the charm of looks,\n" +
                    "\tBut to his foe supposed he must complain,\n" +
                    "\tAnd she steal love's sweet bait from fearful hooks:\n" +
                    "\tBeing held a foe, he may not have access\n" +
                    "\tTo breathe such vows as lovers use to swear;\n" +
                    "\tAnd she as much in love, her means much less\n" +
                    "\tTo meet her new-beloved any where:\n" +
                    "\tBut passion lends them power, time means, to meet\n" +
                    "\tTempering extremities with extreme sweet.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE I\tA lane by the wall of Capulet's orchard.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "ROMEO\tCan I go forward when my heart is here?\n" +
                    "\tTurn back, dull earth, and find thy centre out.\n" +
                    "\n" +
                    "\t[He climbs the wall, and leaps down within it]\n" +
                    "\n" +
                    "\t[Enter BENVOLIO and MERCUTIO]\n" +
                    "\n" +
                    "BENVOLIO\tRomeo! my cousin Romeo!\n" +
                    "\n" +
                    "MERCUTIO\tHe is wise;\n" +
                    "\tAnd, on my lie, hath stol'n him home to bed.\n" +
                    "\n" +
                    "BENVOLIO\tHe ran this way, and leap'd this orchard wall:\n" +
                    "\tCall, good Mercutio.\n" +
                    "\n" +
                    "MERCUTIO\tNay, I'll conjure too.\n" +
                    "\tRomeo! humours! madman! passion! lover!\n" +
                    "\tAppear thou in the likeness of a sigh:\n" +
                    "\tSpeak but one rhyme, and I am satisfied;\n" +
                    "\tCry but 'Ay me!' pronounce but 'love' and 'dove;'\n" +
                    "\tSpeak to my gossip Venus one fair word,\n" +
                    "\tOne nick-name for her purblind son and heir,\n" +
                    "\tYoung Adam Cupid, he that shot so trim,\n" +
                    "\tWhen King Cophetua loved the beggar-maid!\n" +
                    "\tHe heareth not, he stirreth not, he moveth not;\n" +
                    "\tThe ape is dead, and I must conjure him.\n" +
                    "\tI conjure thee by Rosaline's bright eyes,\n" +
                    "\tBy her high forehead and her scarlet lip,\n" +
                    "\tBy her fine foot, straight leg and quivering thigh\n" +
                    "\tAnd the demesnes that there adjacent lie,\n" +
                    "\tThat in thy likeness thou appear to us!\n" +
                    "\n" +
                    "BENVOLIO\tAnd if he hear thee, thou wilt anger him.\n" +
                    "\n" +
                    "MERCUTIO\tThis cannot anger him: 'twould anger him\n" +
                    "\tTo raise a spirit in his mistress' circle\n" +
                    "\tOf some strange nature, letting it there stand\n" +
                    "\tTill she had laid it and conjured it down;\n" +
                    "\tThat were some spite: my invocation\n" +
                    "\tIs fair and honest, and in his mistress' name\n" +
                    "\tI conjure only but to raise up him.\n" +
                    "\n" +
                    "BENVOLIO\tCome, he hath hid himself among these trees,\n" +
                    "\tTo be consorted with the humorous night:\n" +
                    "\tBlind is his love and best befits the dark.\n" +
                    "\n" +
                    "MERCUTIO\tIf love be blind, love cannot hit the mark.\n" +
                    "\tNow will he sit under a medlar tree,\n" +
                    "\tAnd wish his mistress were that kind of fruit\n" +
                    "\tAs maids call medlars, when they laugh alone.\n" +
                    "\tRomeo, that she were, O, that she were\n" +
                    "\tAn open et caetera, thou a poperin pear!\n" +
                    "\tRomeo, good night: I'll to my truckle-bed;\n" +
                    "\tThis field-bed is too cold for me to sleep:\n" +
                    "\tCome, shall we go?\n" +
                    "\n" +
                    "BENVOLIO\t                  Go, then; for 'tis in vain\n" +
                    "\tTo seek him here that means not to be found.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE II\tCapulet's orchard.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "ROMEO\tHe jests at scars that never felt a wound.\n" +
                    "\n" +
                    "\t[JULIET appears above at a window]\n" +
                    "\n" +
                    "\tBut, soft! what light through yonder window breaks?\n" +
                    "\tIt is the east, and Juliet is the sun.\n" +
                    "\tArise, fair sun, and kill the envious moon,\n" +
                    "\tWho is already sick and pale with grief,\n" +
                    "\tThat thou her maid art far more fair than she:\n" +
                    "\tBe not her maid, since she is envious;\n" +
                    "\tHer vestal livery is but sick and green\n" +
                    "\tAnd none but fools do wear it; cast it off.\n" +
                    "\tIt is my lady, O, it is my love!\n" +
                    "\tO, that she knew she were!\n" +
                    "\tShe speaks yet she says nothing: what of that?\n" +
                    "\tHer eye discourses; I will answer it.\n" +
                    "\tI am too bold, 'tis not to me she speaks:\n" +
                    "\tTwo of the fairest stars in all the heaven,\n" +
                    "\tHaving some business, do entreat her eyes\n" +
                    "\tTo twinkle in their spheres till they return.\n" +
                    "\tWhat if her eyes were there, they in her head?\n" +
                    "\tThe brightness of her cheek would shame those stars,\n" +
                    "\tAs daylight doth a lamp; her eyes in heaven\n" +
                    "\tWould through the airy region stream so bright\n" +
                    "\tThat birds would sing and think it were not night.\n" +
                    "\tSee, how she leans her cheek upon her hand!\n" +
                    "\tO, that I were a glove upon that hand,\n" +
                    "\tThat I might touch that cheek!\n" +
                    "\n" +
                    "JULIET\tAy me!\n" +
                    "\n" +
                    "ROMEO\tShe speaks:\n" +
                    "\tO, speak again, bright angel! for thou art\n" +
                    "\tAs glorious to this night, being o'er my head\n" +
                    "\tAs is a winged messenger of heaven\n" +
                    "\tUnto the white-upturned wondering eyes\n" +
                    "\tOf mortals that fall back to gaze on him\n" +
                    "\tWhen he bestrides the lazy-pacing clouds\n" +
                    "\tAnd sails upon the bosom of the air.\n" +
                    "\n" +
                    "JULIET\tO Romeo, Romeo! wherefore art thou Romeo?\n" +
                    "\tDeny thy father and refuse thy name;\n" +
                    "\tOr, if thou wilt not, be but sworn my love,\n" +
                    "\tAnd I'll no longer be a Capulet.\n" +
                    "\n" +
                    "ROMEO\t[Aside]  Shall I hear more, or shall I speak at this?\n" +
                    "\n" +
                    "JULIET\t'Tis but thy name that is my enemy;\n" +
                    "\tThou art thyself, though not a Montague.\n" +
                    "\tWhat's Montague? it is nor hand, nor foot,\n" +
                    "\tNor arm, nor face, nor any other part\n" +
                    "\tBelonging to a man. O, be some other name!\n" +
                    "\tWhat's in a name? that which we call a rose\n" +
                    "\tBy any other name would smell as sweet;\n" +
                    "\tSo Romeo would, were he not Romeo call'd,\n" +
                    "\tRetain that dear perfection which he owes\n" +
                    "\tWithout that title. Romeo, doff thy name,\n" +
                    "\tAnd for that name which is no part of thee\n" +
                    "\tTake all myself.\n" +
                    "\n" +
                    "ROMEO\t                  I take thee at thy word:\n" +
                    "\tCall me but love, and I'll be new baptized;\n" +
                    "\tHenceforth I never will be Romeo.\n" +
                    "\n" +
                    "JULIET\tWhat man art thou that thus bescreen'd in night\n" +
                    "\tSo stumblest on my counsel?\n" +
                    "\n" +
                    "ROMEO\tBy a name\n" +
                    "\tI know not how to tell thee who I am:\n" +
                    "\tMy name, dear saint, is hateful to myself,\n" +
                    "\tBecause it is an enemy to thee;\n" +
                    "\tHad I it written, I would tear the word.\n" +
                    "\n" +
                    "JULIET\tMy ears have not yet drunk a hundred words\n" +
                    "\tOf that tongue's utterance, yet I know the sound:\n" +
                    "\tArt thou not Romeo and a Montague?\n" +
                    "\n" +
                    "ROMEO\tNeither, fair saint, if either thee dislike.\n" +
                    "\n" +
                    "JULIET\tHow camest thou hither, tell me, and wherefore?\n" +
                    "\tThe orchard walls are high and hard to climb,\n" +
                    "\tAnd the place death, considering who thou art,\n" +
                    "\tIf any of my kinsmen find thee here.\n" +
                    "\n" +
                    "ROMEO\tWith love's light wings did I o'er-perch these walls;\n" +
                    "\tFor stony limits cannot hold love out,\n" +
                    "\tAnd what love can do that dares love attempt;\n" +
                    "\tTherefore thy kinsmen are no let to me.\n" +
                    "\n" +
                    "JULIET\tIf they do see thee, they will murder thee.\n" +
                    "\n" +
                    "ROMEO\tAlack, there lies more peril in thine eye\n" +
                    "\tThan twenty of their swords: look thou but sweet,\n" +
                    "\tAnd I am proof against their enmity.\n" +
                    "\n" +
                    "JULIET\tI would not for the world they saw thee here.\n" +
                    "\n" +
                    "ROMEO\tI have night's cloak to hide me from their sight;\n" +
                    "\tAnd but thou love me, let them find me here:\n" +
                    "\tMy life were better ended by their hate,\n" +
                    "\tThan death prorogued, wanting of thy love.\n" +
                    "\n" +
                    "JULIET\tBy whose direction found'st thou out this place?\n" +
                    "\n" +
                    "ROMEO\tBy love, who first did prompt me to inquire;\n" +
                    "\tHe lent me counsel and I lent him eyes.\n" +
                    "\tI am no pilot; yet, wert thou as far\n" +
                    "\tAs that vast shore wash'd with the farthest sea,\n" +
                    "\tI would adventure for such merchandise.\n" +
                    "\n" +
                    "JULIET\tThou know'st the mask of night is on my face,\n" +
                    "\tElse would a maiden blush bepaint my cheek\n" +
                    "\tFor that which thou hast heard me speak to-night\n" +
                    "\tFain would I dwell on form, fain, fain deny\n" +
                    "\tWhat I have spoke: but farewell compliment!\n" +
                    "\tDost thou love me? I know thou wilt say 'Ay,'\n" +
                    "\tAnd I will take thy word: yet if thou swear'st,\n" +
                    "\tThou mayst prove false; at lovers' perjuries\n" +
                    "\tThen say, Jove laughs. O gentle Romeo,\n" +
                    "\tIf thou dost love, pronounce it faithfully:\n" +
                    "\tOr if thou think'st I am too quickly won,\n" +
                    "\tI'll frown and be perverse an say thee nay,\n" +
                    "\tSo thou wilt woo; but else, not for the world.\n" +
                    "\tIn truth, fair Montague, I am too fond,\n" +
                    "\tAnd therefore thou mayst think my 'havior light:\n" +
                    "\tBut trust me, gentleman, I'll prove more true\n" +
                    "\tThan those that have more cunning to be strange.\n" +
                    "\tI should have been more strange, I must confess,\n" +
                    "\tBut that thou overheard'st, ere I was ware,\n" +
                    "\tMy true love's passion: therefore pardon me,\n" +
                    "\tAnd not impute this yielding to light love,\n" +
                    "\tWhich the dark night hath so discovered.\n" +
                    "\n" +
                    "ROMEO\tLady, by yonder blessed moon I swear\n" +
                    "\tThat tips with silver all these fruit-tree tops--\n" +
                    "\n" +
                    "JULIET\tO, swear not by the moon, the inconstant moon,\n" +
                    "\tThat monthly changes in her circled orb,\n" +
                    "\tLest that thy love prove likewise variable.\n" +
                    "\n" +
                    "ROMEO\tWhat shall I swear by?\n" +
                    "\n" +
                    "JULIET\tDo not swear at all;\n" +
                    "\tOr, if thou wilt, swear by thy gracious self,\n" +
                    "\tWhich is the god of my idolatry,\n" +
                    "\tAnd I'll believe thee.\n" +
                    "\n" +
                    "ROMEO\tIf my heart's dear love--\n" +
                    "\n" +
                    "JULIET\tWell, do not swear: although I joy in thee,\n" +
                    "\tI have no joy of this contract to-night:\n" +
                    "\tIt is too rash, too unadvised, too sudden;\n" +
                    "\tToo like the lightning, which doth cease to be\n" +
                    "\tEre one can say 'It lightens.' Sweet, good night!\n" +
                    "\tThis bud of love, by summer's ripening breath,\n" +
                    "\tMay prove a beauteous flower when next we meet.\n" +
                    "\tGood night, good night! as sweet repose and rest\n" +
                    "\tCome to thy heart as that within my breast!\n" +
                    "\n" +
                    "ROMEO\tO, wilt thou leave me so unsatisfied?\n" +
                    "\n" +
                    "JULIET\tWhat satisfaction canst thou have to-night?\n" +
                    "\n" +
                    "ROMEO\tThe exchange of thy love's faithful vow for mine.\n" +
                    "\n" +
                    "JULIET\tI gave thee mine before thou didst request it:\n" +
                    "\tAnd yet I would it were to give again.\n" +
                    "\n" +
                    "ROMEO\tWouldst thou withdraw it? for what purpose, love?\n" +
                    "\n" +
                    "JULIET\tBut to be frank, and give it thee again.\n" +
                    "\tAnd yet I wish but for the thing I have:\n" +
                    "\tMy bounty is as boundless as the sea,\n" +
                    "\tMy love as deep; the more I give to thee,\n" +
                    "\tThe more I have, for both are infinite.\n" +
                    "\n" +
                    "\t[Nurse calls within]\n" +
                    "\n" +
                    "\tI hear some noise within; dear love, adieu!\n" +
                    "\tAnon, good nurse! Sweet Montague, be true.\n" +
                    "\tStay but a little, I will come again.\n" +
                    "\n" +
                    "\t[Exit, above]\n" +
                    "\n" +
                    "ROMEO\tO blessed, blessed night! I am afeard.\n" +
                    "\tBeing in night, all this is but a dream,\n" +
                    "\tToo flattering-sweet to be substantial.\n" +
                    "\n" +
                    "\t[Re-enter JULIET, above]\n" +
                    "\n" +
                    "JULIET\tThree words, dear Romeo, and good night indeed.\n" +
                    "\tIf that thy bent of love be honourable,\n" +
                    "\tThy purpose marriage, send me word to-morrow,\n" +
                    "\tBy one that I'll procure to come to thee,\n" +
                    "\tWhere and what time thou wilt perform the rite;\n" +
                    "\tAnd all my fortunes at thy foot I'll lay\n" +
                    "\tAnd follow thee my lord throughout the world.\n" +
                    "\n" +
                    "Nurse\t[Within]  Madam!\n" +
                    "\n" +
                    "JULIET\tI come, anon.--But if thou mean'st not well,\n" +
                    "\tI do beseech thee--\n" +
                    "\n" +
                    "Nurse\t[Within]  Madam!\n" +
                    "\n" +
                    "JULIET\tBy and by, I come:--\n" +
                    "\tTo cease thy suit, and leave me to my grief:\n" +
                    "\tTo-morrow will I send.\n" +
                    "\n" +
                    "ROMEO\tSo thrive my soul--\n" +
                    "\n" +
                    "JULIET\tA thousand times good night!\n" +
                    "\n" +
                    "\t[Exit, above]\n" +
                    "\n" +
                    "ROMEO\tA thousand times the worse, to want thy light.\n" +
                    "\tLove goes toward love, as schoolboys from\n" +
                    "\ttheir books,\n" +
                    "\tBut love from love, toward school with heavy looks.\n" +
                    "\n" +
                    "\t[Retiring]\n" +
                    "\n" +
                    "\t[Re-enter JULIET, above]\n" +
                    "\n" +
                    "JULIET\tHist! Romeo, hist! O, for a falconer's voice,\n" +
                    "\tTo lure this tassel-gentle back again!\n" +
                    "\tBondage is hoarse, and may not speak aloud;\n" +
                    "\tElse would I tear the cave where Echo lies,\n" +
                    "\tAnd make her airy tongue more hoarse than mine,\n" +
                    "\tWith repetition of my Romeo's name.\n" +
                    "\n" +
                    "ROMEO\tIt is my soul that calls upon my name:\n" +
                    "\tHow silver-sweet sound lovers' tongues by night,\n" +
                    "\tLike softest music to attending ears!\n" +
                    "\n" +
                    "JULIET\tRomeo!\n" +
                    "\n" +
                    "ROMEO\t     My dear?\n" +
                    "\n" +
                    "JULIET\t                  At what o'clock to-morrow\n" +
                    "\tShall I send to thee?\n" +
                    "\n" +
                    "ROMEO\tAt the hour of nine.\n" +
                    "\n" +
                    "JULIET\tI will not fail: 'tis twenty years till then.\n" +
                    "\tI have forgot why I did call thee back.\n" +
                    "\n" +
                    "ROMEO\tLet me stand here till thou remember it.\n" +
                    "\n" +
                    "JULIET\tI shall forget, to have thee still stand there,\n" +
                    "\tRemembering how I love thy company.\n" +
                    "\n" +
                    "ROMEO\tAnd I'll still stay, to have thee still forget,\n" +
                    "\tForgetting any other home but this.\n" +
                    "\n" +
                    "JULIET\t'Tis almost morning; I would have thee gone:\n" +
                    "\tAnd yet no further than a wanton's bird;\n" +
                    "\tWho lets it hop a little from her hand,\n" +
                    "\tLike a poor prisoner in his twisted gyves,\n" +
                    "\tAnd with a silk thread plucks it back again,\n" +
                    "\tSo loving-jealous of his liberty.\n" +
                    "\n" +
                    "ROMEO\tI would I were thy bird.\n" +
                    "\n" +
                    "JULIET\tSweet, so would I:\n" +
                    "\tYet I should kill thee with much cherishing.\n" +
                    "\tGood night, good night! parting is such\n" +
                    "\tsweet sorrow,\n" +
                    "\tThat I shall say good night till it be morrow.\n" +
                    "\n" +
                    "\t[Exit above]\n" +
                    "\n" +
                    "ROMEO\tSleep dwell upon thine eyes, peace in thy breast!\n" +
                    "\tWould I were sleep and peace, so sweet to rest!\n" +
                    "\tHence will I to my ghostly father's cell,\n" +
                    "\tHis help to crave, and my dear hap to tell.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE III\tFriar Laurence's cell.\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE, with a basket]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThe grey-eyed morn smiles on the frowning night,\n" +
                    "\tChequering the eastern clouds with streaks of light,\n" +
                    "\tAnd flecked darkness like a drunkard reels\n" +
                    "\tFrom forth day's path and Titan's fiery wheels:\n" +
                    "\tNow, ere the sun advance his burning eye,\n" +
                    "\tThe day to cheer and night's dank dew to dry,\n" +
                    "\tI must up-fill this osier cage of ours\n" +
                    "\tWith baleful weeds and precious-juiced flowers.\n" +
                    "\tThe earth that's nature's mother is her tomb;\n" +
                    "\tWhat is her burying grave that is her womb,\n" +
                    "\tAnd from her womb children of divers kind\n" +
                    "\tWe sucking on her natural bosom find,\n" +
                    "\tMany for many virtues excellent,\n" +
                    "\tNone but for some and yet all different.\n" +
                    "\tO, mickle is the powerful grace that lies\n" +
                    "\tIn herbs, plants, stones, and their true qualities:\n" +
                    "\tFor nought so vile that on the earth doth live\n" +
                    "\tBut to the earth some special good doth give,\n" +
                    "\tNor aught so good but strain'd from that fair use\n" +
                    "\tRevolts from true birth, stumbling on abuse:\n" +
                    "\tVirtue itself turns vice, being misapplied;\n" +
                    "\tAnd vice sometimes by action dignified.\n" +
                    "\tWithin the infant rind of this small flower\n" +
                    "\tPoison hath residence and medicine power:\n" +
                    "\tFor this, being smelt, with that part cheers each part;\n" +
                    "\tBeing tasted, slays all senses with the heart.\n" +
                    "\tTwo such opposed kings encamp them still\n" +
                    "\tIn man as well as herbs, grace and rude will;\n" +
                    "\tAnd where the worser is predominant,\n" +
                    "\tFull soon the canker death eats up that plant.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "ROMEO\tGood morrow, father.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tBenedicite!\n" +
                    "\tWhat early tongue so sweet saluteth me?\n" +
                    "\tYoung son, it argues a distemper'd head\n" +
                    "\tSo soon to bid good morrow to thy bed:\n" +
                    "\tCare keeps his watch in every old man's eye,\n" +
                    "\tAnd where care lodges, sleep will never lie;\n" +
                    "\tBut where unbruised youth with unstuff'd brain\n" +
                    "\tDoth couch his limbs, there golden sleep doth reign:\n" +
                    "\tTherefore thy earliness doth me assure\n" +
                    "\tThou art up-roused by some distemperature;\n" +
                    "\tOr if not so, then here I hit it right,\n" +
                    "\tOur Romeo hath not been in bed to-night.\n" +
                    "\n" +
                    "ROMEO\tThat last is true; the sweeter rest was mine.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tGod pardon sin! wast thou with Rosaline?\n" +
                    "\n" +
                    "ROMEO\tWith Rosaline, my ghostly father? no;\n" +
                    "\tI have forgot that name, and that name's woe.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThat's my good son: but where hast thou been, then?\n" +
                    "\n" +
                    "ROMEO\tI'll tell thee, ere thou ask it me again.\n" +
                    "\tI have been feasting with mine enemy,\n" +
                    "\tWhere on a sudden one hath wounded me,\n" +
                    "\tThat's by me wounded: both our remedies\n" +
                    "\tWithin thy help and holy physic lies:\n" +
                    "\tI bear no hatred, blessed man, for, lo,\n" +
                    "\tMy intercession likewise steads my foe.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tBe plain, good son, and homely in thy drift;\n" +
                    "\tRiddling confession finds but riddling shrift.\n" +
                    "\n" +
                    "ROMEO\tThen plainly know my heart's dear love is set\n" +
                    "\tOn the fair daughter of rich Capulet:\n" +
                    "\tAs mine on hers, so hers is set on mine;\n" +
                    "\tAnd all combined, save what thou must combine\n" +
                    "\tBy holy marriage: when and where and how\n" +
                    "\tWe met, we woo'd and made exchange of vow,\n" +
                    "\tI'll tell thee as we pass; but this I pray,\n" +
                    "\tThat thou consent to marry us to-day.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHoly Saint Francis, what a change is here!\n" +
                    "\tIs Rosaline, whom thou didst love so dear,\n" +
                    "\tSo soon forsaken? young men's love then lies\n" +
                    "\tNot truly in their hearts, but in their eyes.\n" +
                    "\tJesu Maria, what a deal of brine\n" +
                    "\tHath wash'd thy sallow cheeks for Rosaline!\n" +
                    "\tHow much salt water thrown away in waste,\n" +
                    "\tTo season love, that of it doth not taste!\n" +
                    "\tThe sun not yet thy sighs from heaven clears,\n" +
                    "\tThy old groans ring yet in my ancient ears;\n" +
                    "\tLo, here upon thy cheek the stain doth sit\n" +
                    "\tOf an old tear that is not wash'd off yet:\n" +
                    "\tIf e'er thou wast thyself and these woes thine,\n" +
                    "\tThou and these woes were all for Rosaline:\n" +
                    "\tAnd art thou changed? pronounce this sentence then,\n" +
                    "\tWomen may fall, when there's no strength in men.\n" +
                    "\n" +
                    "ROMEO\tThou chid'st me oft for loving Rosaline.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tFor doting, not for loving, pupil mine.\n" +
                    "\n" +
                    "ROMEO\tAnd bad'st me bury love.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tNot in a grave,\n" +
                    "\tTo lay one in, another out to have.\n" +
                    "\n" +
                    "ROMEO\tI pray thee, chide not; she whom I love now\n" +
                    "\tDoth grace for grace and love for love allow;\n" +
                    "\tThe other did not so.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tO, she knew well\n" +
                    "\tThy love did read by rote and could not spell.\n" +
                    "\tBut come, young waverer, come, go with me,\n" +
                    "\tIn one respect I'll thy assistant be;\n" +
                    "\tFor this alliance may so happy prove,\n" +
                    "\tTo turn your households' rancour to pure love.\n" +
                    "\n" +
                    "ROMEO\tO, let us hence; I stand on sudden haste.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tWisely and slow; they stumble that run fast.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE IV\tA street.\n" +
                    "\n" +
                    "\t[Enter BENVOLIO and MERCUTIO]\n" +
                    "\n" +
                    "MERCUTIO\tWhere the devil should this Romeo be?\n" +
                    "\tCame he not home to-night?\n" +
                    "\n" +
                    "BENVOLIO\tNot to his father's; I spoke with his man.\n" +
                    "\n" +
                    "MERCUTIO\tAh, that same pale hard-hearted wench, that Rosaline.\n" +
                    "\tTorments him so, that he will sure run mad.\n" +
                    "\n" +
                    "BENVOLIO\tTybalt, the kinsman of old Capulet,\n" +
                    "\tHath sent a letter to his father's house.\n" +
                    "\n" +
                    "MERCUTIO\tA challenge, on my life.\n" +
                    "\n" +
                    "BENVOLIO\tRomeo will answer it.\n" +
                    "\n" +
                    "MERCUTIO\tAny man that can write may answer a letter.\n" +
                    "\n" +
                    "BENVOLIO\tNay, he will answer the letter's master, how he\n" +
                    "\tdares, being dared.\n" +
                    "\n" +
                    "MERCUTIO\tAlas poor Romeo! he is already dead; stabbed with a\n" +
                    "\twhite wench's black eye; shot through the ear with a\n" +
                    "\tlove-song; the very pin of his heart cleft with the\n" +
                    "\tblind bow-boy's butt-shaft: and is he a man to\n" +
                    "\tencounter Tybalt?\n" +
                    "\n" +
                    "BENVOLIO\tWhy, what is Tybalt?\n" +
                    "\n" +
                    "MERCUTIO\tMore than prince of cats, I can tell you. O, he is\n" +
                    "\tthe courageous captain of compliments. He fights as\n" +
                    "\tyou sing prick-song, keeps time, distance, and\n" +
                    "\tproportion; rests me his minim rest, one, two, and\n" +
                    "\tthe third in your bosom: the very butcher of a silk\n" +
                    "\tbutton, a duellist, a duellist; a gentleman of the\n" +
                    "\tvery first house, of the first and second cause:\n" +
                    "\tah, the immortal passado! the punto reverso! the\n" +
                    "\thai!\n" +
                    "\n" +
                    "BENVOLIO\tThe what?\n" +
                    "\n" +
                    "MERCUTIO\tThe pox of such antic, lisping, affecting\n" +
                    "\tfantasticoes; these new tuners of accents! 'By Jesu,\n" +
                    "\ta very good blade! a very tall man! a very good\n" +
                    "\twhore!' Why, is not this a lamentable thing,\n" +
                    "\tgrandsire, that we should be thus afflicted with\n" +
                    "\tthese strange flies, these fashion-mongers, these\n" +
                    "\tperdona-mi's, who stand so much on the new form,\n" +
                    "\tthat they cannot at ease on the old bench? O, their\n" +
                    "\tbones, their bones!\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "BENVOLIO\tHere comes Romeo, here comes Romeo.\n" +
                    "\n" +
                    "MERCUTIO\tWithout his roe, like a dried herring: flesh, flesh,\n" +
                    "\thow art thou fishified! Now is he for the numbers\n" +
                    "\tthat Petrarch flowed in: Laura to his lady was but a\n" +
                    "\tkitchen-wench; marry, she had a better love to\n" +
                    "\tbe-rhyme her; Dido a dowdy; Cleopatra a gipsy;\n" +
                    "\tHelen and Hero hildings and harlots; Thisbe a grey\n" +
                    "\teye or so, but not to the purpose. Signior\n" +
                    "\tRomeo, bon jour! there's a French salutation\n" +
                    "\tto your French slop. You gave us the counterfeit\n" +
                    "\tfairly last night.\n" +
                    "\n" +
                    "ROMEO\tGood morrow to you both. What counterfeit did I give you?\n" +
                    "\n" +
                    "MERCUTIO\tThe ship, sir, the slip; can you not conceive?\n" +
                    "\n" +
                    "ROMEO\tPardon, good Mercutio, my business was great; and in\n" +
                    "\tsuch a case as mine a man may strain courtesy.\n" +
                    "\n" +
                    "MERCUTIO\tThat's as much as to say, such a case as yours\n" +
                    "\tconstrains a man to bow in the hams.\n" +
                    "\n" +
                    "ROMEO\tMeaning, to court'sy.\n" +
                    "\n" +
                    "MERCUTIO\tThou hast most kindly hit it.\n" +
                    "\n" +
                    "ROMEO\tA most courteous exposition.\n" +
                    "\n" +
                    "MERCUTIO\tNay, I am the very pink of courtesy.\n" +
                    "\n" +
                    "ROMEO\tPink for flower.\n" +
                    "\n" +
                    "MERCUTIO\tRight.\n" +
                    "\n" +
                    "ROMEO\tWhy, then is my pump well flowered.\n" +
                    "\n" +
                    "MERCUTIO\tWell said: follow me this jest now till thou hast\n" +
                    "\tworn out thy pump, that when the single sole of it\n" +
                    "\tis worn, the jest may remain after the wearing sole singular.\n" +
                    "\n" +
                    "ROMEO\tO single-soled jest, solely singular for the\n" +
                    "\tsingleness.\n" +
                    "\n" +
                    "MERCUTIO\tCome between us, good Benvolio; my wits faint.\n" +
                    "\n" +
                    "ROMEO\tSwitch and spurs, switch and spurs; or I'll cry a match.\n" +
                    "\n" +
                    "MERCUTIO\tNay, if thy wits run the wild-goose chase, I have\n" +
                    "\tdone, for thou hast more of the wild-goose in one of\n" +
                    "\tthy wits than, I am sure, I have in my whole five:\n" +
                    "\twas I with you there for the goose?\n" +
                    "\n" +
                    "ROMEO\tThou wast never with me for any thing when thou wast\n" +
                    "\tnot there for the goose.\n" +
                    "\n" +
                    "MERCUTIO\tI will bite thee by the ear for that jest.\n" +
                    "\n" +
                    "ROMEO\tNay, good goose, bite not.\n" +
                    "\n" +
                    "MERCUTIO\tThy wit is a very bitter sweeting; it is a most\n" +
                    "\tsharp sauce.\n" +
                    "\n" +
                    "ROMEO\tAnd is it not well served in to a sweet goose?\n" +
                    "\n" +
                    "MERCUTIO\tO here's a wit of cheveril, that stretches from an\n" +
                    "\tinch narrow to an ell broad!\n" +
                    "\n" +
                    "ROMEO\tI stretch it out for that word 'broad;' which added\n" +
                    "\tto the goose, proves thee far and wide a broad goose.\n" +
                    "\n" +
                    "MERCUTIO\tWhy, is not this better now than groaning for love?\n" +
                    "\tnow art thou sociable, now art thou Romeo; now art\n" +
                    "\tthou what thou art, by art as well as by nature:\n" +
                    "\tfor this drivelling love is like a great natural,\n" +
                    "\tthat runs lolling up and down to hide his bauble in a hole.\n" +
                    "\n" +
                    "BENVOLIO\tStop there, stop there.\n" +
                    "\n" +
                    "MERCUTIO\tThou desirest me to stop in my tale against the hair.\n" +
                    "\n" +
                    "BENVOLIO\tThou wouldst else have made thy tale large.\n" +
                    "\n" +
                    "MERCUTIO\tO, thou art deceived; I would have made it short:\n" +
                    "\tfor I was come to the whole depth of my tale; and\n" +
                    "\tmeant, indeed, to occupy the argument no longer.\n" +
                    "\n" +
                    "ROMEO\tHere's goodly gear!\n" +
                    "\n" +
                    "\t[Enter Nurse and PETER]\n" +
                    "\n" +
                    "MERCUTIO\tA sail, a sail!\n" +
                    "\n" +
                    "BENVOLIO\tTwo, two; a shirt and a smock.\n" +
                    "\n" +
                    "Nurse\tPeter!\n" +
                    "\n" +
                    "PETER\tAnon!\n" +
                    "\n" +
                    "Nurse\tMy fan, Peter.\n" +
                    "\n" +
                    "MERCUTIO\tGood Peter, to hide her face; for her fan's the\n" +
                    "\tfairer face.\n" +
                    "\n" +
                    "Nurse\tGod ye good morrow, gentlemen.\n" +
                    "\n" +
                    "MERCUTIO\tGod ye good den, fair gentlewoman.\n" +
                    "\n" +
                    "Nurse\tIs it good den?\n" +
                    "\n" +
                    "MERCUTIO\t'Tis no less, I tell you, for the bawdy hand of the\n" +
                    "\tdial is now upon the prick of noon.\n" +
                    "\n" +
                    "Nurse\tOut upon you! what a man are you!\n" +
                    "\n" +
                    "ROMEO\tOne, gentlewoman, that God hath made for himself to\n" +
                    "\tmar.\n" +
                    "\n" +
                    "Nurse\tBy my troth, it is well said; 'for himself to mar,'\n" +
                    "\tquoth a'? Gentlemen, can any of you tell me where I\n" +
                    "\tmay find the young Romeo?\n" +
                    "\n" +
                    "ROMEO\tI can tell you; but young Romeo will be older when\n" +
                    "\tyou have found him than he was when you sought him:\n" +
                    "\tI am the youngest of that name, for fault of a worse.\n" +
                    "\n" +
                    "Nurse\tYou say well.\n" +
                    "\n" +
                    "MERCUTIO\tYea, is the worst well? very well took, i' faith;\n" +
                    "\twisely, wisely.\n" +
                    "\n" +
                    "Nurse\tif you be he, sir, I desire some confidence with\n" +
                    "\tyou.\n" +
                    "\n" +
                    "BENVOLIO\tShe will indite him to some supper.\n" +
                    "\n" +
                    "MERCUTIO\tA bawd, a bawd, a bawd! so ho!\n" +
                    "\n" +
                    "ROMEO\tWhat hast thou found?\n" +
                    "\n" +
                    "MERCUTIO\tNo hare, sir; unless a hare, sir, in a lenten pie,\n" +
                    "\tthat is something stale and hoar ere it be spent.\n" +
                    "\n" +
                    "\t[Sings]\n" +
                    "\n" +
                    "\tAn old hare hoar,\n" +
                    "\tAnd an old hare hoar,\n" +
                    "\tIs very good meat in lent\n" +
                    "\tBut a hare that is hoar\n" +
                    "\tIs too much for a score,\n" +
                    "\tWhen it hoars ere it be spent.\n" +
                    "\tRomeo, will you come to your father's? we'll\n" +
                    "\tto dinner, thither.\n" +
                    "\n" +
                    "ROMEO\tI will follow you.\n" +
                    "\n" +
                    "MERCUTIO\tFarewell, ancient lady; farewell,\n" +
                    "\n" +
                    "\t[Singing]\n" +
                    "\n" +
                    "\t'lady, lady, lady.'\n" +
                    "\n" +
                    "\t[Exeunt MERCUTIO and BENVOLIO]\n" +
                    "\n" +
                    "Nurse\tMarry, farewell! I pray you, sir, what saucy\n" +
                    "\tmerchant was this, that was so full of his ropery?\n" +
                    "\n" +
                    "ROMEO\tA gentleman, nurse, that loves to hear himself talk,\n" +
                    "\tand will speak more in a minute than he will stand\n" +
                    "\tto in a month.\n" +
                    "\n" +
                    "Nurse\tAn a' speak any thing against me, I'll take him\n" +
                    "\tdown, an a' were lustier than he is, and twenty such\n" +
                    "\tJacks; and if I cannot, I'll find those that shall.\n" +
                    "\tScurvy knave! I am none of his flirt-gills; I am\n" +
                    "\tnone of his skains-mates. And thou must stand by\n" +
                    "\ttoo, and suffer every knave to use me at his pleasure?\n" +
                    "\n" +
                    "PETER\tI saw no man use you a pleasure; if I had, my weapon\n" +
                    "\tshould quickly have been out, I warrant you: I dare\n" +
                    "\tdraw as soon as another man, if I see occasion in a\n" +
                    "\tgood quarrel, and the law on my side.\n" +
                    "\n" +
                    "Nurse\tNow, afore God, I am so vexed, that every part about\n" +
                    "\tme quivers. Scurvy knave! Pray you, sir, a word:\n" +
                    "\tand as I told you, my young lady bade me inquire you\n" +
                    "\tout; what she bade me say, I will keep to myself:\n" +
                    "\tbut first let me tell ye, if ye should lead her into\n" +
                    "\ta fool's paradise, as they say, it were a very gross\n" +
                    "\tkind of behavior, as they say: for the gentlewoman\n" +
                    "\tis young; and, therefore, if you should deal double\n" +
                    "\twith her, truly it were an ill thing to be offered\n" +
                    "\tto any gentlewoman, and very weak dealing.\n" +
                    "\n" +
                    "ROMEO\tNurse, commend me to thy lady and mistress. I\n" +
                    "\tprotest unto thee--\n" +
                    "\n" +
                    "Nurse\tGood heart, and, i' faith, I will tell her as much:\n" +
                    "\tLord, Lord, she will be a joyful woman.\n" +
                    "\n" +
                    "ROMEO\tWhat wilt thou tell her, nurse? thou dost not mark me.\n" +
                    "\n" +
                    "Nurse\tI will tell her, sir, that you do protest; which, as\n" +
                    "\tI take it, is a gentlemanlike offer.\n" +
                    "\n" +
                    "ROMEO\tBid her devise\n" +
                    "\tSome means to come to shrift this afternoon;\n" +
                    "\tAnd there she shall at Friar Laurence' cell\n" +
                    "\tBe shrived and married. Here is for thy pains.\n" +
                    "\n" +
                    "Nurse\tNo truly sir; not a penny.\n" +
                    "\n" +
                    "ROMEO\tGo to; I say you shall.\n" +
                    "\n" +
                    "Nurse\tThis afternoon, sir? well, she shall be there.\n" +
                    "\n" +
                    "ROMEO\tAnd stay, good nurse, behind the abbey wall:\n" +
                    "\tWithin this hour my man shall be with thee\n" +
                    "\tAnd bring thee cords made like a tackled stair;\n" +
                    "\tWhich to the high top-gallant of my joy\n" +
                    "\tMust be my convoy in the secret night.\n" +
                    "\tFarewell; be trusty, and I'll quit thy pains:\n" +
                    "\tFarewell; commend me to thy mistress.\n" +
                    "\n" +
                    "Nurse\tNow God in heaven bless thee! Hark you, sir.\n" +
                    "\n" +
                    "ROMEO\tWhat say'st thou, my dear nurse?\n" +
                    "\n" +
                    "Nurse\tIs your man secret? Did you ne'er hear say,\n" +
                    "\tTwo may keep counsel, putting one away?\n" +
                    "\n" +
                    "ROMEO\tI warrant thee, my man's as true as steel.\n" +
                    "\n" +
                    "NURSE\tWell, sir; my mistress is the sweetest lady--Lord,\n" +
                    "\tLord! when 'twas a little prating thing:--O, there\n" +
                    "\tis a nobleman in town, one Paris, that would fain\n" +
                    "\tlay knife aboard; but she, good soul, had as lief\n" +
                    "\tsee a toad, a very toad, as see him. I anger her\n" +
                    "\tsometimes and tell her that Paris is the properer\n" +
                    "\tman; but, I'll warrant you, when I say so, she looks\n" +
                    "\tas pale as any clout in the versal world. Doth not\n" +
                    "\trosemary and Romeo begin both with a letter?\n" +
                    "\n" +
                    "ROMEO\tAy, nurse; what of that? both with an R.\n" +
                    "\n" +
                    "Nurse\tAh. mocker! that's the dog's name; R is for\n" +
                    "\tthe--No; I know it begins with some other\n" +
                    "\tletter:--and she hath the prettiest sententious of\n" +
                    "\tit, of you and rosemary, that it would do you good\n" +
                    "\tto hear it.\n" +
                    "\n" +
                    "ROMEO\tCommend me to thy lady.\n" +
                    "\n" +
                    "Nurse\tAy, a thousand times.\n" +
                    "\n" +
                    "\t[Exit Romeo]\n" +
                    "\tPeter!\n" +
                    "\n" +
                    "PETER\tAnon!\n" +
                    "\n" +
                    "Nurse\tPeter, take my fan, and go before and apace.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE V\tCapulet's orchard.\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "JULIET\tThe clock struck nine when I did send the nurse;\n" +
                    "\tIn half an hour she promised to return.\n" +
                    "\tPerchance she cannot meet him: that's not so.\n" +
                    "\tO, she is lame! love's heralds should be thoughts,\n" +
                    "\tWhich ten times faster glide than the sun's beams,\n" +
                    "\tDriving back shadows over louring hills:\n" +
                    "\tTherefore do nimble-pinion'd doves draw love,\n" +
                    "\tAnd therefore hath the wind-swift Cupid wings.\n" +
                    "\tNow is the sun upon the highmost hill\n" +
                    "\tOf this day's journey, and from nine till twelve\n" +
                    "\tIs three long hours, yet she is not come.\n" +
                    "\tHad she affections and warm youthful blood,\n" +
                    "\tShe would be as swift in motion as a ball;\n" +
                    "\tMy words would bandy her to my sweet love,\n" +
                    "\tAnd his to me:\n" +
                    "\tBut old folks, many feign as they were dead;\n" +
                    "\tUnwieldy, slow, heavy and pale as lead.\n" +
                    "\tO God, she comes!\n" +
                    "\n" +
                    "\t[Enter Nurse and PETER]\n" +
                    "\n" +
                    "\tO honey nurse, what news?\n" +
                    "\tHast thou met with him? Send thy man away.\n" +
                    "\n" +
                    "Nurse\tPeter, stay at the gate.\n" +
                    "\n" +
                    "\t[Exit PETER]\n" +
                    "\n" +
                    "JULIET\tNow, good sweet nurse,--O Lord, why look'st thou sad?\n" +
                    "\tThough news be sad, yet tell them merrily;\n" +
                    "\tIf good, thou shamest the music of sweet news\n" +
                    "\tBy playing it to me with so sour a face.\n" +
                    "\n" +
                    "Nurse\tI am a-weary, give me leave awhile:\n" +
                    "\tFie, how my bones ache! what a jaunt have I had!\n" +
                    "\n" +
                    "JULIET\tI would thou hadst my bones, and I thy news:\n" +
                    "\tNay, come, I pray thee, speak; good, good nurse, speak.\n" +
                    "\n" +
                    "Nurse\tJesu, what haste? can you not stay awhile?\n" +
                    "\tDo you not see that I am out of breath?\n" +
                    "\n" +
                    "JULIET\tHow art thou out of breath, when thou hast breath\n" +
                    "\tTo say to me that thou art out of breath?\n" +
                    "\tThe excuse that thou dost make in this delay\n" +
                    "\tIs longer than the tale thou dost excuse.\n" +
                    "\tIs thy news good, or bad? answer to that;\n" +
                    "\tSay either, and I'll stay the circumstance:\n" +
                    "\tLet me be satisfied, is't good or bad?\n" +
                    "\n" +
                    "Nurse\tWell, you have made a simple choice; you know not\n" +
                    "\thow to choose a man: Romeo! no, not he; though his\n" +
                    "\tface be better than any man's, yet his leg excels\n" +
                    "\tall men's; and for a hand, and a foot, and a body,\n" +
                    "\tthough they be not to be talked on, yet they are\n" +
                    "\tpast compare: he is not the flower of courtesy,\n" +
                    "\tbut, I'll warrant him, as gentle as a lamb. Go thy\n" +
                    "\tways, wench; serve God. What, have you dined at home?\n" +
                    "\n" +
                    "JULIET\tNo, no: but all this did I know before.\n" +
                    "\tWhat says he of our marriage? what of that?\n" +
                    "\n" +
                    "Nurse\tLord, how my head aches! what a head have I!\n" +
                    "\tIt beats as it would fall in twenty pieces.\n" +
                    "\tMy back o' t' other side,--O, my back, my back!\n" +
                    "\tBeshrew your heart for sending me about,\n" +
                    "\tTo catch my death with jaunting up and down!\n" +
                    "\n" +
                    "JULIET\tI' faith, I am sorry that thou art not well.\n" +
                    "\tSweet, sweet, sweet nurse, tell me, what says my love?\n" +
                    "\n" +
                    "Nurse\tYour love says, like an honest gentleman, and a\n" +
                    "\tcourteous, and a kind, and a handsome, and, I\n" +
                    "\twarrant, a virtuous,--Where is your mother?\n" +
                    "\n" +
                    "JULIET\tWhere is my mother! why, she is within;\n" +
                    "\tWhere should she be? How oddly thou repliest!\n" +
                    "\t'Your love says, like an honest gentleman,\n" +
                    "\tWhere is your mother?'\n" +
                    "\n" +
                    "Nurse\tO God's lady dear!\n" +
                    "\tAre you so hot? marry, come up, I trow;\n" +
                    "\tIs this the poultice for my aching bones?\n" +
                    "\tHenceforward do your messages yourself.\n" +
                    "\n" +
                    "JULIET\tHere's such a coil! come, what says Romeo?\n" +
                    "\n" +
                    "Nurse\tHave you got leave to go to shrift to-day?\n" +
                    "\n" +
                    "JULIET\tI have.\n" +
                    "\n" +
                    "Nurse\tThen hie you hence to Friar Laurence' cell;\n" +
                    "\tThere stays a husband to make you a wife:\n" +
                    "\tNow comes the wanton blood up in your cheeks,\n" +
                    "\tThey'll be in scarlet straight at any news.\n" +
                    "\tHie you to church; I must another way,\n" +
                    "\tTo fetch a ladder, by the which your love\n" +
                    "\tMust climb a bird's nest soon when it is dark:\n" +
                    "\tI am the drudge and toil in your delight,\n" +
                    "\tBut you shall bear the burden soon at night.\n" +
                    "\tGo; I'll to dinner: hie you to the cell.\n" +
                    "\n" +
                    "JULIET\tHie to high fortune! Honest nurse, farewell.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT II\n" +
                    "\n" +
                    "SCENE VI\tFriar Laurence's cell.\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE and ROMEO]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tSo smile the heavens upon this holy act,\n" +
                    "\tThat after hours with sorrow chide us not!\n" +
                    "\n" +
                    "ROMEO\tAmen, amen! but come what sorrow can,\n" +
                    "\tIt cannot countervail the exchange of joy\n" +
                    "\tThat one short minute gives me in her sight:\n" +
                    "\tDo thou but close our hands with holy words,\n" +
                    "\tThen love-devouring death do what he dare;\n" +
                    "\tIt is enough I may but call her mine.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThese violent delights have violent ends\n" +
                    "\tAnd in their triumph die, like fire and powder,\n" +
                    "\tWhich as they kiss consume: the sweetest honey\n" +
                    "\tIs loathsome in his own deliciousness\n" +
                    "\tAnd in the taste confounds the appetite:\n" +
                    "\tTherefore love moderately; long love doth so;\n" +
                    "\tToo swift arrives as tardy as too slow.\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "\tHere comes the lady: O, so light a foot\n" +
                    "\tWill ne'er wear out the everlasting flint:\n" +
                    "\tA lover may bestride the gossamer\n" +
                    "\tThat idles in the wanton summer air,\n" +
                    "\tAnd yet not fall; so light is vanity.\n" +
                    "\n" +
                    "JULIET\tGood even to my ghostly confessor.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tRomeo shall thank thee, daughter, for us both.\n" +
                    "\n" +
                    "JULIET\tAs much to him, else is his thanks too much.\n" +
                    "\n" +
                    "ROMEO\tAh, Juliet, if the measure of thy joy\n" +
                    "\tBe heap'd like mine and that thy skill be more\n" +
                    "\tTo blazon it, then sweeten with thy breath\n" +
                    "\tThis neighbour air, and let rich music's tongue\n" +
                    "\tUnfold the imagined happiness that both\n" +
                    "\tReceive in either by this dear encounter.\n" +
                    "\n" +
                    "JULIET\tConceit, more rich in matter than in words,\n" +
                    "\tBrags of his substance, not of ornament:\n" +
                    "\tThey are but beggars that can count their worth;\n" +
                    "\tBut my true love is grown to such excess\n" +
                    "\tI cannot sum up sum of half my wealth.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tCome, come with me, and we will make short work;\n" +
                    "\tFor, by your leaves, you shall not stay alone\n" +
                    "\tTill holy church incorporate two in one.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT III\n" +
                    "\n" +
                    "SCENE I\tA public place.\n" +
                    "\n" +
                    "\t[Enter MERCUTIO, BENVOLIO, Page, and Servants]\n" +
                    "\n" +
                    "BENVOLIO\tI pray thee, good Mercutio, let's retire:\n" +
                    "\tThe day is hot, the Capulets abroad,\n" +
                    "\tAnd, if we meet, we shall not scape a brawl;\n" +
                    "\tFor now, these hot days, is the mad blood stirring.\n" +
                    "\n" +
                    "MERCUTIO\tThou art like one of those fellows that when he\n" +
                    "\tenters the confines of a tavern claps me his sword\n" +
                    "\tupon the table and says 'God send me no need of\n" +
                    "\tthee!' and by the operation of the second cup draws\n" +
                    "\tit on the drawer, when indeed there is no need.\n" +
                    "\n" +
                    "BENVOLIO\tAm I like such a fellow?\n" +
                    "\n" +
                    "MERCUTIO\tCome, come, thou art as hot a Jack in thy mood as\n" +
                    "\tany in Italy, and as soon moved to be moody, and as\n" +
                    "\tsoon moody to be moved.\n" +
                    "\n" +
                    "BENVOLIO\tAnd what to?\n" +
                    "\n" +
                    "MERCUTIO\tNay, an there were two such, we should have none\n" +
                    "\tshortly, for one would kill the other. Thou! why,\n" +
                    "\tthou wilt quarrel with a man that hath a hair more,\n" +
                    "\tor a hair less, in his beard, than thou hast: thou\n" +
                    "\twilt quarrel with a man for cracking nuts, having no\n" +
                    "\tother reason but because thou hast hazel eyes: what\n" +
                    "\teye but such an eye would spy out such a quarrel?\n" +
                    "\tThy head is as fun of quarrels as an egg is full of\n" +
                    "\tmeat, and yet thy head hath been beaten as addle as\n" +
                    "\tan egg for quarrelling: thou hast quarrelled with a\n" +
                    "\tman for coughing in the street, because he hath\n" +
                    "\twakened thy dog that hath lain asleep in the sun:\n" +
                    "\tdidst thou not fall out with a tailor for wearing\n" +
                    "\this new doublet before Easter? with another, for\n" +
                    "\ttying his new shoes with old riband? and yet thou\n" +
                    "\twilt tutor me from quarrelling!\n" +
                    "\n" +
                    "BENVOLIO\tAn I were so apt to quarrel as thou art, any man\n" +
                    "\tshould buy the fee-simple of my life for an hour and a quarter.\n" +
                    "\n" +
                    "MERCUTIO\tThe fee-simple! O simple!\n" +
                    "\n" +
                    "BENVOLIO\tBy my head, here come the Capulets.\n" +
                    "\n" +
                    "MERCUTIO\tBy my heel, I care not.\n" +
                    "\n" +
                    "\t[Enter TYBALT and others]\n" +
                    "\n" +
                    "TYBALT\tFollow me close, for I will speak to them.\n" +
                    "\tGentlemen, good den: a word with one of you.\n" +
                    "\n" +
                    "MERCUTIO\tAnd but one word with one of us? couple it with\n" +
                    "\tsomething; make it a word and a blow.\n" +
                    "\n" +
                    "TYBALT\tYou shall find me apt enough to that, sir, an you\n" +
                    "\twill give me occasion.\n" +
                    "\n" +
                    "MERCUTIO\tCould you not take some occasion without giving?\n" +
                    "\n" +
                    "TYBALT\tMercutio, thou consort'st with Romeo,--\n" +
                    "\n" +
                    "MERCUTIO\tConsort! what, dost thou make us minstrels? an\n" +
                    "\tthou make minstrels of us, look to hear nothing but\n" +
                    "\tdiscords: here's my fiddlestick; here's that shall\n" +
                    "\tmake you dance. 'Zounds, consort!\n" +
                    "\n" +
                    "BENVOLIO\tWe talk here in the public haunt of men:\n" +
                    "\tEither withdraw unto some private place,\n" +
                    "\tAnd reason coldly of your grievances,\n" +
                    "\tOr else depart; here all eyes gaze on us.\n" +
                    "\n" +
                    "MERCUTIO\tMen's eyes were made to look, and let them gaze;\n" +
                    "\tI will not budge for no man's pleasure, I.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "TYBALT\tWell, peace be with you, sir: here comes my man.\n" +
                    "\n" +
                    "MERCUTIO\tBut I'll be hanged, sir, if he wear your livery:\n" +
                    "\tMarry, go before to field, he'll be your follower;\n" +
                    "\tYour worship in that sense may call him 'man.'\n" +
                    "\n" +
                    "TYBALT\tRomeo, the hate I bear thee can afford\n" +
                    "\tNo better term than this,--thou art a villain.\n" +
                    "\n" +
                    "ROMEO\tTybalt, the reason that I have to love thee\n" +
                    "\tDoth much excuse the appertaining rage\n" +
                    "\tTo such a greeting: villain am I none;\n" +
                    "\tTherefore farewell; I see thou know'st me not.\n" +
                    "\n" +
                    "TYBALT\tBoy, this shall not excuse the injuries\n" +
                    "\tThat thou hast done me; therefore turn and draw.\n" +
                    "\n" +
                    "ROMEO\tI do protest, I never injured thee,\n" +
                    "\tBut love thee better than thou canst devise,\n" +
                    "\tTill thou shalt know the reason of my love:\n" +
                    "\tAnd so, good Capulet,--which name I tender\n" +
                    "\tAs dearly as my own,--be satisfied.\n" +
                    "\n" +
                    "MERCUTIO\tO calm, dishonourable, vile submission!\n" +
                    "\tAlla stoccata carries it away.\n" +
                    "\n" +
                    "\t[Draws]\n" +
                    "\n" +
                    "\tTybalt, you rat-catcher, will you walk?\n" +
                    "\n" +
                    "TYBALT\tWhat wouldst thou have with me?\n" +
                    "\n" +
                    "MERCUTIO\tGood king of cats, nothing but one of your nine\n" +
                    "\tlives; that I mean to make bold withal, and as you\n" +
                    "\tshall use me hereafter, drybeat the rest of the\n" +
                    "\teight. Will you pluck your sword out of his pitcher\n" +
                    "\tby the ears? make haste, lest mine be about your\n" +
                    "\tears ere it be out.\n" +
                    "\n" +
                    "TYBALT\tI am for you.\n" +
                    "\n" +
                    "\t[Drawing]\n" +
                    "\n" +
                    "ROMEO\tGentle Mercutio, put thy rapier up.\n" +
                    "\n" +
                    "MERCUTIO\tCome, sir, your passado.\n" +
                    "\n" +
                    "\t[They fight]\n" +
                    "\n" +
                    "ROMEO\tDraw, Benvolio; beat down their weapons.\n" +
                    "\tGentlemen, for shame, forbear this outrage!\n" +
                    "\tTybalt, Mercutio, the prince expressly hath\n" +
                    "\tForbidden bandying in Verona streets:\n" +
                    "\tHold, Tybalt! good Mercutio!\n" +
                    "\n" +
                    "\t[TYBALT under ROMEO's arm stabs MERCUTIO, and flies\n" +
                    "\twith his followers]\n" +
                    "\n" +
                    "MERCUTIO\tI am hurt.\n" +
                    "\tA plague o' both your houses! I am sped.\n" +
                    "\tIs he gone, and hath nothing?\n" +
                    "\n" +
                    "BENVOLIO\tWhat, art thou hurt?\n" +
                    "\n" +
                    "MERCUTIO\tAy, ay, a scratch, a scratch; marry, 'tis enough.\n" +
                    "\tWhere is my page? Go, villain, fetch a surgeon.\n" +
                    "\n" +
                    "\t[Exit Page]\n" +
                    "\n" +
                    "ROMEO\tCourage, man; the hurt cannot be much.\n" +
                    "\n" +
                    "MERCUTIO\tNo, 'tis not so deep as a well, nor so wide as a\n" +
                    "\tchurch-door; but 'tis enough,'twill serve: ask for\n" +
                    "\tme to-morrow, and you shall find me a grave man. I\n" +
                    "\tam peppered, I warrant, for this world. A plague o'\n" +
                    "\tboth your houses! 'Zounds, a dog, a rat, a mouse, a\n" +
                    "\tcat, to scratch a man to death! a braggart, a\n" +
                    "\trogue, a villain, that fights by the book of\n" +
                    "\tarithmetic! Why the devil came you between us? I\n" +
                    "\twas hurt under your arm.\n" +
                    "\n" +
                    "ROMEO\tI thought all for the best.\n" +
                    "\n" +
                    "MERCUTIO\tHelp me into some house, Benvolio,\n" +
                    "\tOr I shall faint. A plague o' both your houses!\n" +
                    "\tThey have made worms' meat of me: I have it,\n" +
                    "\tAnd soundly too: your houses!\n" +
                    "\n" +
                    "\t[Exeunt MERCUTIO and BENVOLIO]\n" +
                    "\n" +
                    "ROMEO\tThis gentleman, the prince's near ally,\n" +
                    "\tMy very friend, hath got his mortal hurt\n" +
                    "\tIn my behalf; my reputation stain'd\n" +
                    "\tWith Tybalt's slander,--Tybalt, that an hour\n" +
                    "\tHath been my kinsman! O sweet Juliet,\n" +
                    "\tThy beauty hath made me effeminate\n" +
                    "\tAnd in my temper soften'd valour's steel!\n" +
                    "\n" +
                    "\t[Re-enter BENVOLIO]\n" +
                    "\n" +
                    "BENVOLIO\tO Romeo, Romeo, brave Mercutio's dead!\n" +
                    "\tThat gallant spirit hath aspired the clouds,\n" +
                    "\tWhich too untimely here did scorn the earth.\n" +
                    "\n" +
                    "ROMEO\tThis day's black fate on more days doth depend;\n" +
                    "\tThis but begins the woe, others must end.\n" +
                    "\n" +
                    "BENVOLIO\tHere comes the furious Tybalt back again.\n" +
                    "\n" +
                    "ROMEO\tAlive, in triumph! and Mercutio slain!\n" +
                    "\tAway to heaven, respective lenity,\n" +
                    "\tAnd fire-eyed fury be my conduct now!\n" +
                    "\n" +
                    "\t[Re-enter TYBALT]\n" +
                    "\n" +
                    "\tNow, Tybalt, take the villain back again,\n" +
                    "\tThat late thou gavest me; for Mercutio's soul\n" +
                    "\tIs but a little way above our heads,\n" +
                    "\tStaying for thine to keep him company:\n" +
                    "\tEither thou, or I, or both, must go with him.\n" +
                    "\n" +
                    "TYBALT\tThou, wretched boy, that didst consort him here,\n" +
                    "\tShalt with him hence.\n" +
                    "\n" +
                    "ROMEO\tThis shall determine that.\n" +
                    "\n" +
                    "\t[They fight; TYBALT falls]\n" +
                    "\n" +
                    "BENVOLIO\tRomeo, away, be gone!\n" +
                    "\tThe citizens are up, and Tybalt slain.\n" +
                    "\tStand not amazed: the prince will doom thee death,\n" +
                    "\tIf thou art taken: hence, be gone, away!\n" +
                    "\n" +
                    "ROMEO\tO, I am fortune's fool!\n" +
                    "\n" +
                    "BENVOLIO\tWhy dost thou stay?\n" +
                    "\n" +
                    "\t[Exit ROMEO]\n" +
                    "\n" +
                    "\t[Enter Citizens, &c]\n" +
                    "\n" +
                    "First Citizen\tWhich way ran he that kill'd Mercutio?\n" +
                    "\tTybalt, that murderer, which way ran he?\n" +
                    "\n" +
                    "BENVOLIO\tThere lies that Tybalt.\n" +
                    "\n" +
                    "First Citizen\tUp, sir, go with me;\n" +
                    "\tI charge thee in the princes name, obey.\n" +
                    "\n" +
                    "\t[Enter Prince, attended; MONTAGUE, CAPULET, their\n" +
                    "\tWives, and others]\n" +
                    "\n" +
                    "PRINCE\tWhere are the vile beginners of this fray?\n" +
                    "\n" +
                    "BENVOLIO\tO noble prince, I can discover all\n" +
                    "\tThe unlucky manage of this fatal brawl:\n" +
                    "\tThere lies the man, slain by young Romeo,\n" +
                    "\tThat slew thy kinsman, brave Mercutio.\n" +
                    "\n" +
                    "LADY CAPULET\tTybalt, my cousin! O my brother's child!\n" +
                    "\tO prince! O cousin! husband! O, the blood is spilt\n" +
                    "\tO my dear kinsman! Prince, as thou art true,\n" +
                    "\tFor blood of ours, shed blood of Montague.\n" +
                    "\tO cousin, cousin!\n" +
                    "\n" +
                    "PRINCE\tBenvolio, who began this bloody fray?\n" +
                    "\n" +
                    "BENVOLIO\tTybalt, here slain, whom Romeo's hand did slay;\n" +
                    "\tRomeo that spoke him fair, bade him bethink\n" +
                    "\tHow nice the quarrel was, and urged withal\n" +
                    "\tYour high displeasure: all this uttered\n" +
                    "\tWith gentle breath, calm look, knees humbly bow'd,\n" +
                    "\tCould not take truce with the unruly spleen\n" +
                    "\tOf Tybalt deaf to peace, but that he tilts\n" +
                    "\tWith piercing steel at bold Mercutio's breast,\n" +
                    "\tWho all as hot, turns deadly point to point,\n" +
                    "\tAnd, with a martial scorn, with one hand beats\n" +
                    "\tCold death aside, and with the other sends\n" +
                    "\tIt back to Tybalt, whose dexterity,\n" +
                    "\tRetorts it: Romeo he cries aloud,\n" +
                    "\t'Hold, friends! friends, part!' and, swifter than\n" +
                    "\this tongue,\n" +
                    "\tHis agile arm beats down their fatal points,\n" +
                    "\tAnd 'twixt them rushes; underneath whose arm\n" +
                    "\tAn envious thrust from Tybalt hit the life\n" +
                    "\tOf stout Mercutio, and then Tybalt fled;\n" +
                    "\tBut by and by comes back to Romeo,\n" +
                    "\tWho had but newly entertain'd revenge,\n" +
                    "\tAnd to 't they go like lightning, for, ere I\n" +
                    "\tCould draw to part them, was stout Tybalt slain.\n" +
                    "\tAnd, as he fell, did Romeo turn and fly.\n" +
                    "\tThis is the truth, or let Benvolio die.\n" +
                    "\n" +
                    "LADY CAPULET\tHe is a kinsman to the Montague;\n" +
                    "\tAffection makes him false; he speaks not true:\n" +
                    "\tSome twenty of them fought in this black strife,\n" +
                    "\tAnd all those twenty could but kill one life.\n" +
                    "\tI beg for justice, which thou, prince, must give;\n" +
                    "\tRomeo slew Tybalt, Romeo must not live.\n" +
                    "\n" +
                    "PRINCE\tRomeo slew him, he slew Mercutio;\n" +
                    "\tWho now the price of his dear blood doth owe?\n" +
                    "\n" +
                    "MONTAGUE\tNot Romeo, prince, he was Mercutio's friend;\n" +
                    "\tHis fault concludes but what the law should end,\n" +
                    "\tThe life of Tybalt.\n" +
                    "\n" +
                    "PRINCE\tAnd for that offence\n" +
                    "\tImmediately we do exile him hence:\n" +
                    "\tI have an interest in your hate's proceeding,\n" +
                    "\tMy blood for your rude brawls doth lie a-bleeding;\n" +
                    "\tBut I'll amerce you with so strong a fine\n" +
                    "\tThat you shall all repent the loss of mine:\n" +
                    "\tI will be deaf to pleading and excuses;\n" +
                    "\tNor tears nor prayers shall purchase out abuses:\n" +
                    "\tTherefore use none: let Romeo hence in haste,\n" +
                    "\tElse, when he's found, that hour is his last.\n" +
                    "\tBear hence this body and attend our will:\n" +
                    "\tMercy but murders, pardoning those that kill.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT III\n" +
                    "\n" +
                    "SCENE II\tCapulet's orchard.\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "JULIET\tGallop apace, you fiery-footed steeds,\n" +
                    "\tTowards Phoebus' lodging: such a wagoner\n" +
                    "\tAs Phaethon would whip you to the west,\n" +
                    "\tAnd bring in cloudy night immediately.\n" +
                    "\tSpread thy close curtain, love-performing night,\n" +
                    "\tThat runaway's eyes may wink and Romeo\n" +
                    "\tLeap to these arms, untalk'd of and unseen.\n" +
                    "\tLovers can see to do their amorous rites\n" +
                    "\tBy their own beauties; or, if love be blind,\n" +
                    "\tIt best agrees with night. Come, civil night,\n" +
                    "\tThou sober-suited matron, all in black,\n" +
                    "\tAnd learn me how to lose a winning match,\n" +
                    "\tPlay'd for a pair of stainless maidenhoods:\n" +
                    "\tHood my unmann'd blood, bating in my cheeks,\n" +
                    "\tWith thy black mantle; till strange love, grown bold,\n" +
                    "\tThink true love acted simple modesty.\n" +
                    "\tCome, night; come, Romeo; come, thou day in night;\n" +
                    "\tFor thou wilt lie upon the wings of night\n" +
                    "\tWhiter than new snow on a raven's back.\n" +
                    "\tCome, gentle night, come, loving, black-brow'd night,\n" +
                    "\tGive me my Romeo; and, when he shall die,\n" +
                    "\tTake him and cut him out in little stars,\n" +
                    "\tAnd he will make the face of heaven so fine\n" +
                    "\tThat all the world will be in love with night\n" +
                    "\tAnd pay no worship to the garish sun.\n" +
                    "\tO, I have bought the mansion of a love,\n" +
                    "\tBut not possess'd it, and, though I am sold,\n" +
                    "\tNot yet enjoy'd: so tedious is this day\n" +
                    "\tAs is the night before some festival\n" +
                    "\tTo an impatient child that hath new robes\n" +
                    "\tAnd may not wear them. O, here comes my nurse,\n" +
                    "\tAnd she brings news; and every tongue that speaks\n" +
                    "\tBut Romeo's name speaks heavenly eloquence.\n" +
                    "\n" +
                    "\t[Enter Nurse, with cords]\n" +
                    "\n" +
                    "\tNow, nurse, what news? What hast thou there? the cords\n" +
                    "\tThat Romeo bid thee fetch?\n" +
                    "\n" +
                    "Nurse\tAy, ay, the cords.\n" +
                    "\n" +
                    "\t[Throws them down]\n" +
                    "\n" +
                    "JULIET\tAy me! what news? why dost thou wring thy hands?\n" +
                    "\n" +
                    "Nurse\tAh, well-a-day! he's dead, he's dead, he's dead!\n" +
                    "\tWe are undone, lady, we are undone!\n" +
                    "\tAlack the day! he's gone, he's kill'd, he's dead!\n" +
                    "\n" +
                    "JULIET\tCan heaven be so envious?\n" +
                    "\n" +
                    "Nurse\tRomeo can,\n" +
                    "\tThough heaven cannot: O Romeo, Romeo!\n" +
                    "\tWho ever would have thought it? Romeo!\n" +
                    "\n" +
                    "JULIET\tWhat devil art thou, that dost torment me thus?\n" +
                    "\tThis torture should be roar'd in dismal hell.\n" +
                    "\tHath Romeo slain himself? say thou but 'I,'\n" +
                    "\tAnd that bare vowel 'I' shall poison more\n" +
                    "\tThan the death-darting eye of cockatrice:\n" +
                    "\tI am not I, if there be such an I;\n" +
                    "\tOr those eyes shut, that make thee answer 'I.'\n" +
                    "\tIf he be slain, say 'I'; or if not, no:\n" +
                    "\tBrief sounds determine of my weal or woe.\n" +
                    "\n" +
                    "Nurse\tI saw the wound, I saw it with mine eyes,--\n" +
                    "\tGod save the mark!--here on his manly breast:\n" +
                    "\tA piteous corse, a bloody piteous corse;\n" +
                    "\tPale, pale as ashes, all bedaub'd in blood,\n" +
                    "\tAll in gore-blood; I swounded at the sight.\n" +
                    "\n" +
                    "JULIET\tO, break, my heart! poor bankrupt, break at once!\n" +
                    "\tTo prison, eyes, ne'er look on liberty!\n" +
                    "\tVile earth, to earth resign; end motion here;\n" +
                    "\tAnd thou and Romeo press one heavy bier!\n" +
                    "\n" +
                    "Nurse\tO Tybalt, Tybalt, the best friend I had!\n" +
                    "\tO courteous Tybalt! honest gentleman!\n" +
                    "\tThat ever I should live to see thee dead!\n" +
                    "\n" +
                    "JULIET\tWhat storm is this that blows so contrary?\n" +
                    "\tIs Romeo slaughter'd, and is Tybalt dead?\n" +
                    "\tMy dear-loved cousin, and my dearer lord?\n" +
                    "\tThen, dreadful trumpet, sound the general doom!\n" +
                    "\tFor who is living, if those two are gone?\n" +
                    "\n" +
                    "Nurse\tTybalt is gone, and Romeo banished;\n" +
                    "\tRomeo that kill'd him, he is banished.\n" +
                    "\n" +
                    "JULIET\tO God! did Romeo's hand shed Tybalt's blood?\n" +
                    "\n" +
                    "Nurse\tIt did, it did; alas the day, it did!\n" +
                    "\n" +
                    "JULIET\tO serpent heart, hid with a flowering face!\n" +
                    "\tDid ever dragon keep so fair a cave?\n" +
                    "\tBeautiful tyrant! fiend angelical!\n" +
                    "\tDove-feather'd raven! wolvish-ravening lamb!\n" +
                    "\tDespised substance of divinest show!\n" +
                    "\tJust opposite to what thou justly seem'st,\n" +
                    "\tA damned saint, an honourable villain!\n" +
                    "\tO nature, what hadst thou to do in hell,\n" +
                    "\tWhen thou didst bower the spirit of a fiend\n" +
                    "\tIn moral paradise of such sweet flesh?\n" +
                    "\tWas ever book containing such vile matter\n" +
                    "\tSo fairly bound? O that deceit should dwell\n" +
                    "\tIn such a gorgeous palace!\n" +
                    "\n" +
                    "Nurse\tThere's no trust,\n" +
                    "\tNo faith, no honesty in men; all perjured,\n" +
                    "\tAll forsworn, all naught, all dissemblers.\n" +
                    "\tAh, where's my man? give me some aqua vitae:\n" +
                    "\tThese griefs, these woes, these sorrows make me old.\n" +
                    "\tShame come to Romeo!\n" +
                    "\n" +
                    "JULIET\tBlister'd be thy tongue\n" +
                    "\tFor such a wish! he was not born to shame:\n" +
                    "\tUpon his brow shame is ashamed to sit;\n" +
                    "\tFor 'tis a throne where honour may be crown'd\n" +
                    "\tSole monarch of the universal earth.\n" +
                    "\tO, what a beast was I to chide at him!\n" +
                    "\n" +
                    "Nurse\tWill you speak well of him that kill'd your cousin?\n" +
                    "\n" +
                    "JULIET\tShall I speak ill of him that is my husband?\n" +
                    "\tAh, poor my lord, what tongue shall smooth thy name,\n" +
                    "\tWhen I, thy three-hours wife, have mangled it?\n" +
                    "\tBut, wherefore, villain, didst thou kill my cousin?\n" +
                    "\tThat villain cousin would have kill'd my husband:\n" +
                    "\tBack, foolish tears, back to your native spring;\n" +
                    "\tYour tributary drops belong to woe,\n" +
                    "\tWhich you, mistaking, offer up to joy.\n" +
                    "\tMy husband lives, that Tybalt would have slain;\n" +
                    "\tAnd Tybalt's dead, that would have slain my husband:\n" +
                    "\tAll this is comfort; wherefore weep I then?\n" +
                    "\tSome word there was, worser than Tybalt's death,\n" +
                    "\tThat murder'd me: I would forget it fain;\n" +
                    "\tBut, O, it presses to my memory,\n" +
                    "\tLike damned guilty deeds to sinners' minds:\n" +
                    "\t'Tybalt is dead, and Romeo--banished;'\n" +
                    "\tThat 'banished,' that one word 'banished,'\n" +
                    "\tHath slain ten thousand Tybalts. Tybalt's death\n" +
                    "\tWas woe enough, if it had ended there:\n" +
                    "\tOr, if sour woe delights in fellowship\n" +
                    "\tAnd needly will be rank'd with other griefs,\n" +
                    "\tWhy follow'd not, when she said 'Tybalt's dead,'\n" +
                    "\tThy father, or thy mother, nay, or both,\n" +
                    "\tWhich modern lamentations might have moved?\n" +
                    "\tBut with a rear-ward following Tybalt's death,\n" +
                    "\t'Romeo is banished,' to speak that word,\n" +
                    "\tIs father, mother, Tybalt, Romeo, Juliet,\n" +
                    "\tAll slain, all dead. 'Romeo is banished!'\n" +
                    "\tThere is no end, no limit, measure, bound,\n" +
                    "\tIn that word's death; no words can that woe sound.\n" +
                    "\tWhere is my father, and my mother, nurse?\n" +
                    "\n" +
                    "Nurse\tWeeping and wailing over Tybalt's corse:\n" +
                    "\tWill you go to them? I will bring you thither.\n" +
                    "\n" +
                    "JULIET\tWash they his wounds with tears: mine shall be spent,\n" +
                    "\tWhen theirs are dry, for Romeo's banishment.\n" +
                    "\tTake up those cords: poor ropes, you are beguiled,\n" +
                    "\tBoth you and I; for Romeo is exiled:\n" +
                    "\tHe made you for a highway to my bed;\n" +
                    "\tBut I, a maid, die maiden-widowed.\n" +
                    "\tCome, cords, come, nurse; I'll to my wedding-bed;\n" +
                    "\tAnd death, not Romeo, take my maidenhead!\n" +
                    "\n" +
                    "Nurse\tHie to your chamber: I'll find Romeo\n" +
                    "\tTo comfort you: I wot well where he is.\n" +
                    "\tHark ye, your Romeo will be here at night:\n" +
                    "\tI'll to him; he is hid at Laurence' cell.\n" +
                    "\n" +
                    "JULIET\tO, find him! give this ring to my true knight,\n" +
                    "\tAnd bid him come to take his last farewell.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT III\n" +
                    "\n" +
                    "SCENE III\tFriar Laurence's cell.\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tRomeo, come forth; come forth, thou fearful man:\n" +
                    "\tAffliction is enamour'd of thy parts,\n" +
                    "\tAnd thou art wedded to calamity.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "ROMEO\tFather, what news? what is the prince's doom?\n" +
                    "\tWhat sorrow craves acquaintance at my hand,\n" +
                    "\tThat I yet know not?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tToo familiar\n" +
                    "\tIs my dear son with such sour company:\n" +
                    "\tI bring thee tidings of the prince's doom.\n" +
                    "\n" +
                    "ROMEO\tWhat less than dooms-day is the prince's doom?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tA gentler judgment vanish'd from his lips,\n" +
                    "\tNot body's death, but body's banishment.\n" +
                    "\n" +
                    "ROMEO\tHa, banishment! be merciful, say 'death;'\n" +
                    "\tFor exile hath more terror in his look,\n" +
                    "\tMuch more than death: do not say 'banishment.'\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHence from Verona art thou banished:\n" +
                    "\tBe patient, for the world is broad and wide.\n" +
                    "\n" +
                    "ROMEO\tThere is no world without Verona walls,\n" +
                    "\tBut purgatory, torture, hell itself.\n" +
                    "\tHence-banished is banish'd from the world,\n" +
                    "\tAnd world's exile is death: then banished,\n" +
                    "\tIs death mis-term'd: calling death banishment,\n" +
                    "\tThou cutt'st my head off with a golden axe,\n" +
                    "\tAnd smilest upon the stroke that murders me.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tO deadly sin! O rude unthankfulness!\n" +
                    "\tThy fault our law calls death; but the kind prince,\n" +
                    "\tTaking thy part, hath rush'd aside the law,\n" +
                    "\tAnd turn'd that black word death to banishment:\n" +
                    "\tThis is dear mercy, and thou seest it not.\n" +
                    "\n" +
                    "ROMEO\t'Tis torture, and not mercy: heaven is here,\n" +
                    "\tWhere Juliet lives; and every cat and dog\n" +
                    "\tAnd little mouse, every unworthy thing,\n" +
                    "\tLive here in heaven and may look on her;\n" +
                    "\tBut Romeo may not: more validity,\n" +
                    "\tMore honourable state, more courtship lives\n" +
                    "\tIn carrion-flies than Romeo: they my seize\n" +
                    "\tOn the white wonder of dear Juliet's hand\n" +
                    "\tAnd steal immortal blessing from her lips,\n" +
                    "\tWho even in pure and vestal modesty,\n" +
                    "\tStill blush, as thinking their own kisses sin;\n" +
                    "\tBut Romeo may not; he is banished:\n" +
                    "\tFlies may do this, but I from this must fly:\n" +
                    "\tThey are free men, but I am banished.\n" +
                    "\tAnd say'st thou yet that exile is not death?\n" +
                    "\tHadst thou no poison mix'd, no sharp-ground knife,\n" +
                    "\tNo sudden mean of death, though ne'er so mean,\n" +
                    "\tBut 'banished' to kill me?--'banished'?\n" +
                    "\tO friar, the damned use that word in hell;\n" +
                    "\tHowlings attend it: how hast thou the heart,\n" +
                    "\tBeing a divine, a ghostly confessor,\n" +
                    "\tA sin-absolver, and my friend profess'd,\n" +
                    "\tTo mangle me with that word 'banished'?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThou fond mad man, hear me but speak a word.\n" +
                    "\n" +
                    "ROMEO\tO, thou wilt speak again of banishment.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tI'll give thee armour to keep off that word:\n" +
                    "\tAdversity's sweet milk, philosophy,\n" +
                    "\tTo comfort thee, though thou art banished.\n" +
                    "\n" +
                    "ROMEO\tYet 'banished'? Hang up philosophy!\n" +
                    "\tUnless philosophy can make a Juliet,\n" +
                    "\tDisplant a town, reverse a prince's doom,\n" +
                    "\tIt helps not, it prevails not: talk no more.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tO, then I see that madmen have no ears.\n" +
                    "\n" +
                    "ROMEO\tHow should they, when that wise men have no eyes?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tLet me dispute with thee of thy estate.\n" +
                    "\n" +
                    "ROMEO\tThou canst not speak of that thou dost not feel:\n" +
                    "\tWert thou as young as I, Juliet thy love,\n" +
                    "\tAn hour but married, Tybalt murdered,\n" +
                    "\tDoting like me and like me banished,\n" +
                    "\tThen mightst thou speak, then mightst thou tear thy hair,\n" +
                    "\tAnd fall upon the ground, as I do now,\n" +
                    "\tTaking the measure of an unmade grave.\n" +
                    "\n" +
                    "\t[Knocking within]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tArise; one knocks; good Romeo, hide thyself.\n" +
                    "\n" +
                    "ROMEO\tNot I; unless the breath of heartsick groans,\n" +
                    "\tMist-like, infold me from the search of eyes.\n" +
                    "\n" +
                    "\t[Knocking]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHark, how they knock! Who's there? Romeo, arise;\n" +
                    "\tThou wilt be taken. Stay awhile! Stand up;\n" +
                    "\n" +
                    "\t[Knocking]\n" +
                    "\n" +
                    "\tRun to my study. By and by! God's will,\n" +
                    "\tWhat simpleness is this! I come, I come!\n" +
                    "\n" +
                    "\t[Knocking]\n" +
                    "\n" +
                    "\tWho knocks so hard? whence come you? what's your will?\n" +
                    "\n" +
                    "Nurse\t[Within]  Let me come in, and you shall know\n" +
                    "\tmy errand;\n" +
                    "\tI come from Lady Juliet.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tWelcome, then.\n" +
                    "\n" +
                    "\t[Enter Nurse]\n" +
                    "\n" +
                    "Nurse\tO holy friar, O, tell me, holy friar,\n" +
                    "\tWhere is my lady's lord, where's Romeo?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThere on the ground, with his own tears made drunk.\n" +
                    "\n" +
                    "Nurse\tO, he is even in my mistress' case,\n" +
                    "\tJust in her case! O woful sympathy!\n" +
                    "\tPiteous predicament! Even so lies she,\n" +
                    "\tBlubbering and weeping, weeping and blubbering.\n" +
                    "\tStand up, stand up; stand, and you be a man:\n" +
                    "\tFor Juliet's sake, for her sake, rise and stand;\n" +
                    "\tWhy should you fall into so deep an O?\n" +
                    "\n" +
                    "ROMEO\tNurse!\n" +
                    "\n" +
                    "Nurse\tAh sir! ah sir! Well, death's the end of all.\n" +
                    "\n" +
                    "ROMEO\tSpakest thou of Juliet? how is it with her?\n" +
                    "\tDoth she not think me an old murderer,\n" +
                    "\tNow I have stain'd the childhood of our joy\n" +
                    "\tWith blood removed but little from her own?\n" +
                    "\tWhere is she? and how doth she? and what says\n" +
                    "\tMy conceal'd lady to our cancell'd love?\n" +
                    "\n" +
                    "Nurse\tO, she says nothing, sir, but weeps and weeps;\n" +
                    "\tAnd now falls on her bed; and then starts up,\n" +
                    "\tAnd Tybalt calls; and then on Romeo cries,\n" +
                    "\tAnd then down falls again.\n" +
                    "\n" +
                    "ROMEO\tAs if that name,\n" +
                    "\tShot from the deadly level of a gun,\n" +
                    "\tDid murder her; as that name's cursed hand\n" +
                    "\tMurder'd her kinsman. O, tell me, friar, tell me,\n" +
                    "\tIn what vile part of this anatomy\n" +
                    "\tDoth my name lodge? tell me, that I may sack\n" +
                    "\tThe hateful mansion.\n" +
                    "\n" +
                    "\t[Drawing his sword]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHold thy desperate hand:\n" +
                    "\tArt thou a man? thy form cries out thou art:\n" +
                    "\tThy tears are womanish; thy wild acts denote\n" +
                    "\tThe unreasonable fury of a beast:\n" +
                    "\tUnseemly woman in a seeming man!\n" +
                    "\tOr ill-beseeming beast in seeming both!\n" +
                    "\tThou hast amazed me: by my holy order,\n" +
                    "\tI thought thy disposition better temper'd.\n" +
                    "\tHast thou slain Tybalt? wilt thou slay thyself?\n" +
                    "\tAnd stay thy lady too that lives in thee,\n" +
                    "\tBy doing damned hate upon thyself?\n" +
                    "\tWhy rail'st thou on thy birth, the heaven, and earth?\n" +
                    "\tSince birth, and heaven, and earth, all three do meet\n" +
                    "\tIn thee at once; which thou at once wouldst lose.\n" +
                    "\tFie, fie, thou shamest thy shape, thy love, thy wit;\n" +
                    "\tWhich, like a usurer, abound'st in all,\n" +
                    "\tAnd usest none in that true use indeed\n" +
                    "\tWhich should bedeck thy shape, thy love, thy wit:\n" +
                    "\tThy noble shape is but a form of wax,\n" +
                    "\tDigressing from the valour of a man;\n" +
                    "\tThy dear love sworn but hollow perjury,\n" +
                    "\tKilling that love which thou hast vow'd to cherish;\n" +
                    "\tThy wit, that ornament to shape and love,\n" +
                    "\tMisshapen in the conduct of them both,\n" +
                    "\tLike powder in a skitless soldier's flask,\n" +
                    "\tIs set afire by thine own ignorance,\n" +
                    "\tAnd thou dismember'd with thine own defence.\n" +
                    "\tWhat, rouse thee, man! thy Juliet is alive,\n" +
                    "\tFor whose dear sake thou wast but lately dead;\n" +
                    "\tThere art thou happy: Tybalt would kill thee,\n" +
                    "\tBut thou slew'st Tybalt; there are thou happy too:\n" +
                    "\tThe law that threaten'd death becomes thy friend\n" +
                    "\tAnd turns it to exile; there art thou happy:\n" +
                    "\tA pack of blessings lights up upon thy back;\n" +
                    "\tHappiness courts thee in her best array;\n" +
                    "\tBut, like a misbehaved and sullen wench,\n" +
                    "\tThou pout'st upon thy fortune and thy love:\n" +
                    "\tTake heed, take heed, for such die miserable.\n" +
                    "\tGo, get thee to thy love, as was decreed,\n" +
                    "\tAscend her chamber, hence and comfort her:\n" +
                    "\tBut look thou stay not till the watch be set,\n" +
                    "\tFor then thou canst not pass to Mantua;\n" +
                    "\tWhere thou shalt live, till we can find a time\n" +
                    "\tTo blaze your marriage, reconcile your friends,\n" +
                    "\tBeg pardon of the prince, and call thee back\n" +
                    "\tWith twenty hundred thousand times more joy\n" +
                    "\tThan thou went'st forth in lamentation.\n" +
                    "\tGo before, nurse: commend me to thy lady;\n" +
                    "\tAnd bid her hasten all the house to bed,\n" +
                    "\tWhich heavy sorrow makes them apt unto:\n" +
                    "\tRomeo is coming.\n" +
                    "\n" +
                    "Nurse\tO Lord, I could have stay'd here all the night\n" +
                    "\tTo hear good counsel: O, what learning is!\n" +
                    "\tMy lord, I'll tell my lady you will come.\n" +
                    "\n" +
                    "ROMEO\tDo so, and bid my sweet prepare to chide.\n" +
                    "\n" +
                    "Nurse\tHere, sir, a ring she bid me give you, sir:\n" +
                    "\tHie you, make haste, for it grows very late.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "ROMEO\tHow well my comfort is revived by this!\n" +
                    "\n" +
                    "FRIAR LAURENCE\tGo hence; good night; and here stands all your state:\n" +
                    "\tEither be gone before the watch be set,\n" +
                    "\tOr by the break of day disguised from hence:\n" +
                    "\tSojourn in Mantua; I'll find out your man,\n" +
                    "\tAnd he shall signify from time to time\n" +
                    "\tEvery good hap to you that chances here:\n" +
                    "\tGive me thy hand; 'tis late: farewell; good night.\n" +
                    "\n" +
                    "ROMEO\tBut that a joy past joy calls out on me,\n" +
                    "\tIt were a grief, so brief to part with thee: Farewell.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT III\n" +
                    "\n" +
                    "SCENE IV\tA room in Capulet's house.\n" +
                    "\n" +
                    "\t[Enter CAPULET, LADY CAPULET, and PARIS]\n" +
                    "\n" +
                    "CAPULET\tThings have fall'n out, sir, so unluckily,\n" +
                    "\tThat we have had no time to move our daughter:\n" +
                    "\tLook you, she loved her kinsman Tybalt dearly,\n" +
                    "\tAnd so did I:--Well, we were born to die.\n" +
                    "\t'Tis very late, she'll not come down to-night:\n" +
                    "\tI promise you, but for your company,\n" +
                    "\tI would have been a-bed an hour ago.\n" +
                    "\n" +
                    "PARIS\tThese times of woe afford no time to woo.\n" +
                    "\tMadam, good night: commend me to your daughter.\n" +
                    "\n" +
                    "LADY CAPULET\tI will, and know her mind early to-morrow;\n" +
                    "\tTo-night she is mew'd up to her heaviness.\n" +
                    "\n" +
                    "CAPULET\tSir Paris, I will make a desperate tender\n" +
                    "\tOf my child's love: I think she will be ruled\n" +
                    "\tIn all respects by me; nay, more, I doubt it not.\n" +
                    "\tWife, go you to her ere you go to bed;\n" +
                    "\tAcquaint her here of my son Paris' love;\n" +
                    "\tAnd bid her, mark you me, on Wednesday next--\n" +
                    "\tBut, soft! what day is this?\n" +
                    "\n" +
                    "PARIS\tMonday, my lord,\n" +
                    "\n" +
                    "CAPULET\tMonday! ha, ha! Well, Wednesday is too soon,\n" +
                    "\tO' Thursday let it be: o' Thursday, tell her,\n" +
                    "\tShe shall be married to this noble earl.\n" +
                    "\tWill you be ready? do you like this haste?\n" +
                    "\tWe'll keep no great ado,--a friend or two;\n" +
                    "\tFor, hark you, Tybalt being slain so late,\n" +
                    "\tIt may be thought we held him carelessly,\n" +
                    "\tBeing our kinsman, if we revel much:\n" +
                    "\tTherefore we'll have some half a dozen friends,\n" +
                    "\tAnd there an end. But what say you to Thursday?\n" +
                    "\n" +
                    "PARIS\tMy lord, I would that Thursday were to-morrow.\n" +
                    "\n" +
                    "CAPULET\tWell get you gone: o' Thursday be it, then.\n" +
                    "\tGo you to Juliet ere you go to bed,\n" +
                    "\tPrepare her, wife, against this wedding-day.\n" +
                    "\tFarewell, my lord. Light to my chamber, ho!\n" +
                    "\tAfore me! it is so very very late,\n" +
                    "\tThat we may call it early by and by.\n" +
                    "\tGood night.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT III\n" +
                    "\n" +
                    "SCENE V\tCapulet's orchard.\n" +
                    "\n" +
                    "\t[Enter ROMEO and JULIET above, at the window]\n" +
                    "\n" +
                    "JULIET\tWilt thou be gone? it is not yet near day:\n" +
                    "\tIt was the nightingale, and not the lark,\n" +
                    "\tThat pierced the fearful hollow of thine ear;\n" +
                    "\tNightly she sings on yon pomegranate-tree:\n" +
                    "\tBelieve me, love, it was the nightingale.\n" +
                    "\n" +
                    "ROMEO\tIt was the lark, the herald of the morn,\n" +
                    "\tNo nightingale: look, love, what envious streaks\n" +
                    "\tDo lace the severing clouds in yonder east:\n" +
                    "\tNight's candles are burnt out, and jocund day\n" +
                    "\tStands tiptoe on the misty mountain tops.\n" +
                    "\tI must be gone and live, or stay and die.\n" +
                    "\n" +
                    "JULIET\tYon light is not day-light, I know it, I:\n" +
                    "\tIt is some meteor that the sun exhales,\n" +
                    "\tTo be to thee this night a torch-bearer,\n" +
                    "\tAnd light thee on thy way to Mantua:\n" +
                    "\tTherefore stay yet; thou need'st not to be gone.\n" +
                    "\n" +
                    "ROMEO\tLet me be ta'en, let me be put to death;\n" +
                    "\tI am content, so thou wilt have it so.\n" +
                    "\tI'll say yon grey is not the morning's eye,\n" +
                    "\t'Tis but the pale reflex of Cynthia's brow;\n" +
                    "\tNor that is not the lark, whose notes do beat\n" +
                    "\tThe vaulty heaven so high above our heads:\n" +
                    "\tI have more care to stay than will to go:\n" +
                    "\tCome, death, and welcome! Juliet wills it so.\n" +
                    "\tHow is't, my soul? let's talk; it is not day.\n" +
                    "\n" +
                    "JULIET\tIt is, it is: hie hence, be gone, away!\n" +
                    "\tIt is the lark that sings so out of tune,\n" +
                    "\tStraining harsh discords and unpleasing sharps.\n" +
                    "\tSome say the lark makes sweet division;\n" +
                    "\tThis doth not so, for she divideth us:\n" +
                    "\tSome say the lark and loathed toad change eyes,\n" +
                    "\tO, now I would they had changed voices too!\n" +
                    "\tSince arm from arm that voice doth us affray,\n" +
                    "\tHunting thee hence with hunt's-up to the day,\n" +
                    "\tO, now be gone; more light and light it grows.\n" +
                    "\n" +
                    "ROMEO\tMore light and light; more dark and dark our woes!\n" +
                    "\n" +
                    "\t[Enter Nurse, to the chamber]\n" +
                    "\n" +
                    "Nurse\tMadam!\n" +
                    "\n" +
                    "JULIET\tNurse?\n" +
                    "\n" +
                    "Nurse\tYour lady mother is coming to your chamber:\n" +
                    "\tThe day is broke; be wary, look about.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tThen, window, let day in, and let life out.\n" +
                    "\n" +
                    "ROMEO\tFarewell, farewell! one kiss, and I'll descend.\n" +
                    "\n" +
                    "\t[He goeth down]\n" +
                    "\n" +
                    "JULIET\tArt thou gone so? love, lord, ay, husband, friend!\n" +
                    "\tI must hear from thee every day in the hour,\n" +
                    "\tFor in a minute there are many days:\n" +
                    "\tO, by this count I shall be much in years\n" +
                    "\tEre I again behold my Romeo!\n" +
                    "\n" +
                    "ROMEO\tFarewell!\n" +
                    "\tI will omit no opportunity\n" +
                    "\tThat may convey my greetings, love, to thee.\n" +
                    "\n" +
                    "JULIET\tO think'st thou we shall ever meet again?\n" +
                    "\n" +
                    "ROMEO\tI doubt it not; and all these woes shall serve\n" +
                    "\tFor sweet discourses in our time to come.\n" +
                    "\n" +
                    "JULIET\tO God, I have an ill-divining soul!\n" +
                    "\tMethinks I see thee, now thou art below,\n" +
                    "\tAs one dead in the bottom of a tomb:\n" +
                    "\tEither my eyesight fails, or thou look'st pale.\n" +
                    "\n" +
                    "ROMEO\tAnd trust me, love, in my eye so do you:\n" +
                    "\tDry sorrow drinks our blood. Adieu, adieu!\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tO fortune, fortune! all men call thee fickle:\n" +
                    "\tIf thou art fickle, what dost thou with him.\n" +
                    "\tThat is renown'd for faith? Be fickle, fortune;\n" +
                    "\tFor then, I hope, thou wilt not keep him long,\n" +
                    "\tBut send him back.\n" +
                    "\n" +
                    "LADY CAPULET\t[Within]         Ho, daughter! are you up?\n" +
                    "\n" +
                    "JULIET\tWho is't that calls? is it my lady mother?\n" +
                    "\tIs she not down so late, or up so early?\n" +
                    "\tWhat unaccustom'd cause procures her hither?\n" +
                    "\n" +
                    "\t[Enter LADY CAPULET]\n" +
                    "\n" +
                    "LADY CAPULET\tWhy, how now, Juliet!\n" +
                    "\n" +
                    "JULIET\tMadam, I am not well.\n" +
                    "\n" +
                    "LADY CAPULET\tEvermore weeping for your cousin's death?\n" +
                    "\tWhat, wilt thou wash him from his grave with tears?\n" +
                    "\tAn if thou couldst, thou couldst not make him live;\n" +
                    "\tTherefore, have done: some grief shows much of love;\n" +
                    "\tBut much of grief shows still some want of wit.\n" +
                    "\n" +
                    "JULIET\tYet let me weep for such a feeling loss.\n" +
                    "\n" +
                    "LADY CAPULET\tSo shall you feel the loss, but not the friend\n" +
                    "\tWhich you weep for.\n" +
                    "\n" +
                    "JULIET\tFeeling so the loss,\n" +
                    "\tCannot choose but ever weep the friend.\n" +
                    "\n" +
                    "LADY CAPULET\tWell, girl, thou weep'st not so much for his death,\n" +
                    "\tAs that the villain lives which slaughter'd him.\n" +
                    "\n" +
                    "JULIET\tWhat villain madam?\n" +
                    "\n" +
                    "LADY CAPULET\tThat same villain, Romeo.\n" +
                    "\n" +
                    "JULIET\t[Aside]  Villain and he be many miles asunder.--\n" +
                    "\tGod Pardon him! I do, with all my heart;\n" +
                    "\tAnd yet no man like he doth grieve my heart.\n" +
                    "\n" +
                    "LADY CAPULET\tThat is, because the traitor murderer lives.\n" +
                    "\n" +
                    "JULIET\tAy, madam, from the reach of these my hands:\n" +
                    "\tWould none but I might venge my cousin's death!\n" +
                    "\n" +
                    "LADY CAPULET\tWe will have vengeance for it, fear thou not:\n" +
                    "\tThen weep no more. I'll send to one in Mantua,\n" +
                    "\tWhere that same banish'd runagate doth live,\n" +
                    "\tShall give him such an unaccustom'd dram,\n" +
                    "\tThat he shall soon keep Tybalt company:\n" +
                    "\tAnd then, I hope, thou wilt be satisfied.\n" +
                    "\n" +
                    "JULIET\tIndeed, I never shall be satisfied\n" +
                    "\tWith Romeo, till I behold him--dead--\n" +
                    "\tIs my poor heart for a kinsman vex'd.\n" +
                    "\tMadam, if you could find out but a man\n" +
                    "\tTo bear a poison, I would temper it;\n" +
                    "\tThat Romeo should, upon receipt thereof,\n" +
                    "\tSoon sleep in quiet. O, how my heart abhors\n" +
                    "\tTo hear him named, and cannot come to him.\n" +
                    "\tTo wreak the love I bore my cousin\n" +
                    "\tUpon his body that slaughter'd him!\n" +
                    "\n" +
                    "LADY CAPULET\tFind thou the means, and I'll find such a man.\n" +
                    "\tBut now I'll tell thee joyful tidings, girl.\n" +
                    "\n" +
                    "JULIET\tAnd joy comes well in such a needy time:\n" +
                    "\tWhat are they, I beseech your ladyship?\n" +
                    "\n" +
                    "LADY CAPULET\tWell, well, thou hast a careful father, child;\n" +
                    "\tOne who, to put thee from thy heaviness,\n" +
                    "\tHath sorted out a sudden day of joy,\n" +
                    "\tThat thou expect'st not nor I look'd not for.\n" +
                    "\n" +
                    "JULIET\tMadam, in happy time, what day is that?\n" +
                    "\n" +
                    "LADY CAPULET\tMarry, my child, early next Thursday morn,\n" +
                    "\tThe gallant, young and noble gentleman,\n" +
                    "\tThe County Paris, at Saint Peter's Church,\n" +
                    "\tShall happily make thee there a joyful bride.\n" +
                    "\n" +
                    "JULIET\tNow, by Saint Peter's Church and Peter too,\n" +
                    "\tHe shall not make me there a joyful bride.\n" +
                    "\tI wonder at this haste; that I must wed\n" +
                    "\tEre he, that should be husband, comes to woo.\n" +
                    "\tI pray you, tell my lord and father, madam,\n" +
                    "\tI will not marry yet; and, when I do, I swear,\n" +
                    "\tIt shall be Romeo, whom you know I hate,\n" +
                    "\tRather than Paris. These are news indeed!\n" +
                    "\n" +
                    "LADY CAPULET\tHere comes your father; tell him so yourself,\n" +
                    "\tAnd see how he will take it at your hands.\n" +
                    "\n" +
                    "\t[Enter CAPULET and Nurse]\n" +
                    "\n" +
                    "CAPULET\tWhen the sun sets, the air doth drizzle dew;\n" +
                    "\tBut for the sunset of my brother's son\n" +
                    "\tIt rains downright.\n" +
                    "\tHow now! a conduit, girl? what, still in tears?\n" +
                    "\tEvermore showering? In one little body\n" +
                    "\tThou counterfeit'st a bark, a sea, a wind;\n" +
                    "\tFor still thy eyes, which I may call the sea,\n" +
                    "\tDo ebb and flow with tears; the bark thy body is,\n" +
                    "\tSailing in this salt flood; the winds, thy sighs;\n" +
                    "\tWho, raging with thy tears, and they with them,\n" +
                    "\tWithout a sudden calm, will overset\n" +
                    "\tThy tempest-tossed body. How now, wife!\n" +
                    "\tHave you deliver'd to her our decree?\n" +
                    "\n" +
                    "LADY CAPULET\tAy, sir; but she will none, she gives you thanks.\n" +
                    "\tI would the fool were married to her grave!\n" +
                    "\n" +
                    "CAPULET\tSoft! take me with you, take me with you, wife.\n" +
                    "\tHow! will she none? doth she not give us thanks?\n" +
                    "\tIs she not proud? doth she not count her blest,\n" +
                    "\tUnworthy as she is, that we have wrought\n" +
                    "\tSo worthy a gentleman to be her bridegroom?\n" +
                    "\n" +
                    "JULIET\tNot proud, you have; but thankful, that you have:\n" +
                    "\tProud can I never be of what I hate;\n" +
                    "\tBut thankful even for hate, that is meant love.\n" +
                    "\n" +
                    "CAPULET\tHow now, how now, chop-logic! What is this?\n" +
                    "\t'Proud,' and 'I thank you,' and 'I thank you not;'\n" +
                    "\tAnd yet 'not proud,' mistress minion, you,\n" +
                    "\tThank me no thankings, nor, proud me no prouds,\n" +
                    "\tBut fettle your fine joints 'gainst Thursday next,\n" +
                    "\tTo go with Paris to Saint Peter's Church,\n" +
                    "\tOr I will drag thee on a hurdle thither.\n" +
                    "\tOut, you green-sickness carrion! out, you baggage!\n" +
                    "\tYou tallow-face!\n" +
                    "\n" +
                    "LADY CAPULET\t                  Fie, fie! what, are you mad?\n" +
                    "\n" +
                    "JULIET\tGood father, I beseech you on my knees,\n" +
                    "\tHear me with patience but to speak a word.\n" +
                    "\n" +
                    "CAPULET\tHang thee, young baggage! disobedient wretch!\n" +
                    "\tI tell thee what: get thee to church o' Thursday,\n" +
                    "\tOr never after look me in the face:\n" +
                    "\tSpeak not, reply not, do not answer me;\n" +
                    "\tMy fingers itch. Wife, we scarce thought us blest\n" +
                    "\tThat God had lent us but this only child;\n" +
                    "\tBut now I see this one is one too much,\n" +
                    "\tAnd that we have a curse in having her:\n" +
                    "\tOut on her, hilding!\n" +
                    "\n" +
                    "Nurse\tGod in heaven bless her!\n" +
                    "\tYou are to blame, my lord, to rate her so.\n" +
                    "\n" +
                    "CAPULET\tAnd why, my lady wisdom? hold your tongue,\n" +
                    "\tGood prudence; smatter with your gossips, go.\n" +
                    "\n" +
                    "Nurse\tI speak no treason.\n" +
                    "\n" +
                    "CAPULET\tO, God ye god-den.\n" +
                    "\n" +
                    "Nurse\tMay not one speak?\n" +
                    "\n" +
                    "CAPULET\t                  Peace, you mumbling fool!\n" +
                    "\tUtter your gravity o'er a gossip's bowl;\n" +
                    "\tFor here we need it not.\n" +
                    "\n" +
                    "LADY CAPULET\tYou are too hot.\n" +
                    "\n" +
                    "CAPULET\tGod's bread! it makes me mad:\n" +
                    "\tDay, night, hour, tide, time, work, play,\n" +
                    "\tAlone, in company, still my care hath been\n" +
                    "\tTo have her match'd: and having now provided\n" +
                    "\tA gentleman of noble parentage,\n" +
                    "\tOf fair demesnes, youthful, and nobly train'd,\n" +
                    "\tStuff'd, as they say, with honourable parts,\n" +
                    "\tProportion'd as one's thought would wish a man;\n" +
                    "\tAnd then to have a wretched puling fool,\n" +
                    "\tA whining mammet, in her fortune's tender,\n" +
                    "\tTo answer 'I'll not wed; I cannot love,\n" +
                    "\tI am too young; I pray you, pardon me.'\n" +
                    "\tBut, as you will not wed, I'll pardon you:\n" +
                    "\tGraze where you will you shall not house with me:\n" +
                    "\tLook to't, think on't, I do not use to jest.\n" +
                    "\tThursday is near; lay hand on heart, advise:\n" +
                    "\tAn you be mine, I'll give you to my friend;\n" +
                    "\tAnd you be not, hang, beg, starve, die in\n" +
                    "\tthe streets,\n" +
                    "\tFor, by my soul, I'll ne'er acknowledge thee,\n" +
                    "\tNor what is mine shall never do thee good:\n" +
                    "\tTrust to't, bethink you; I'll not be forsworn.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tIs there no pity sitting in the clouds,\n" +
                    "\tThat sees into the bottom of my grief?\n" +
                    "\tO, sweet my mother, cast me not away!\n" +
                    "\tDelay this marriage for a month, a week;\n" +
                    "\tOr, if you do not, make the bridal bed\n" +
                    "\tIn that dim monument where Tybalt lies.\n" +
                    "\n" +
                    "LADY CAPULET\tTalk not to me, for I'll not speak a word:\n" +
                    "\tDo as thou wilt, for I have done with thee.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tO God!--O nurse, how shall this be prevented?\n" +
                    "\tMy husband is on earth, my faith in heaven;\n" +
                    "\tHow shall that faith return again to earth,\n" +
                    "\tUnless that husband send it me from heaven\n" +
                    "\tBy leaving earth? comfort me, counsel me.\n" +
                    "\tAlack, alack, that heaven should practise stratagems\n" +
                    "\tUpon so soft a subject as myself!\n" +
                    "\tWhat say'st thou? hast thou not a word of joy?\n" +
                    "\tSome comfort, nurse.\n" +
                    "\n" +
                    "Nurse\tFaith, here it is.\n" +
                    "\tRomeo is banish'd; and all the world to nothing,\n" +
                    "\tThat he dares ne'er come back to challenge you;\n" +
                    "\tOr, if he do, it needs must be by stealth.\n" +
                    "\tThen, since the case so stands as now it doth,\n" +
                    "\tI think it best you married with the county.\n" +
                    "\tO, he's a lovely gentleman!\n" +
                    "\tRomeo's a dishclout to him: an eagle, madam,\n" +
                    "\tHath not so green, so quick, so fair an eye\n" +
                    "\tAs Paris hath. Beshrew my very heart,\n" +
                    "\tI think you are happy in this second match,\n" +
                    "\tFor it excels your first: or if it did not,\n" +
                    "\tYour first is dead; or 'twere as good he were,\n" +
                    "\tAs living here and you no use of him.\n" +
                    "\n" +
                    "JULIET\tSpeakest thou from thy heart?\n" +
                    "\n" +
                    "Nurse\tAnd from my soul too;\n" +
                    "\tOr else beshrew them both.\n" +
                    "\n" +
                    "JULIET\tAmen!\n" +
                    "\n" +
                    "Nurse\tWhat?\n" +
                    "\n" +
                    "JULIET\tWell, thou hast comforted me marvellous much.\n" +
                    "\tGo in: and tell my lady I am gone,\n" +
                    "\tHaving displeased my father, to Laurence' cell,\n" +
                    "\tTo make confession and to be absolved.\n" +
                    "\n" +
                    "Nurse\tMarry, I will; and this is wisely done.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tAncient damnation! O most wicked fiend!\n" +
                    "\tIs it more sin to wish me thus forsworn,\n" +
                    "\tOr to dispraise my lord with that same tongue\n" +
                    "\tWhich she hath praised him with above compare\n" +
                    "\tSo many thousand times? Go, counsellor;\n" +
                    "\tThou and my bosom henceforth shall be twain.\n" +
                    "\tI'll to the friar, to know his remedy:\n" +
                    "\tIf all else fail, myself have power to die.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT IV\n" +
                    "\n" +
                    "SCENE I\tFriar Laurence's cell.\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE and PARIS]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tOn Thursday, sir? the time is very short.\n" +
                    "\n" +
                    "PARIS\tMy father Capulet will have it so;\n" +
                    "\tAnd I am nothing slow to slack his haste.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tYou say you do not know the lady's mind:\n" +
                    "\tUneven is the course, I like it not.\n" +
                    "\n" +
                    "PARIS\tImmoderately she weeps for Tybalt's death,\n" +
                    "\tAnd therefore have I little talk'd of love;\n" +
                    "\tFor Venus smiles not in a house of tears.\n" +
                    "\tNow, sir, her father counts it dangerous\n" +
                    "\tThat she doth give her sorrow so much sway,\n" +
                    "\tAnd in his wisdom hastes our marriage,\n" +
                    "\tTo stop the inundation of her tears;\n" +
                    "\tWhich, too much minded by herself alone,\n" +
                    "\tMay be put from her by society:\n" +
                    "\tNow do you know the reason of this haste.\n" +
                    "\n" +
                    "FRIAR LAURENCE\t[Aside]  I would I knew not why it should be slow'd.\n" +
                    "\tLook, sir, here comes the lady towards my cell.\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "PARIS\tHappily met, my lady and my wife!\n" +
                    "\n" +
                    "JULIET\tThat may be, sir, when I may be a wife.\n" +
                    "\n" +
                    "PARIS\tThat may be must be, love, on Thursday next.\n" +
                    "\n" +
                    "JULIET\tWhat must be shall be.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThat's a certain text.\n" +
                    "\n" +
                    "PARIS\tCome you to make confession to this father?\n" +
                    "\n" +
                    "JULIET\tTo answer that, I should confess to you.\n" +
                    "\n" +
                    "PARIS\tDo not deny to him that you love me.\n" +
                    "\n" +
                    "JULIET\tI will confess to you that I love him.\n" +
                    "\n" +
                    "PARIS\tSo will ye, I am sure, that you love me.\n" +
                    "\n" +
                    "JULIET\tIf I do so, it will be of more price,\n" +
                    "\tBeing spoke behind your back, than to your face.\n" +
                    "\n" +
                    "PARIS\tPoor soul, thy face is much abused with tears.\n" +
                    "\n" +
                    "JULIET\tThe tears have got small victory by that;\n" +
                    "\tFor it was bad enough before their spite.\n" +
                    "\n" +
                    "PARIS\tThou wrong'st it, more than tears, with that report.\n" +
                    "\n" +
                    "JULIET\tThat is no slander, sir, which is a truth;\n" +
                    "\tAnd what I spake, I spake it to my face.\n" +
                    "\n" +
                    "PARIS\tThy face is mine, and thou hast slander'd it.\n" +
                    "\n" +
                    "JULIET\tIt may be so, for it is not mine own.\n" +
                    "\tAre you at leisure, holy father, now;\n" +
                    "\tOr shall I come to you at evening mass?\n" +
                    "\n" +
                    "FRIAR LAURENCE\tMy leisure serves me, pensive daughter, now.\n" +
                    "\tMy lord, we must entreat the time alone.\n" +
                    "\n" +
                    "PARIS\tGod shield I should disturb devotion!\n" +
                    "\tJuliet, on Thursday early will I rouse ye:\n" +
                    "\tTill then, adieu; and keep this holy kiss.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "JULIET\tO shut the door! and when thou hast done so,\n" +
                    "\tCome weep with me; past hope, past cure, past help!\n" +
                    "\n" +
                    "FRIAR LAURENCE\tAh, Juliet, I already know thy grief;\n" +
                    "\tIt strains me past the compass of my wits:\n" +
                    "\tI hear thou must, and nothing may prorogue it,\n" +
                    "\tOn Thursday next be married to this county.\n" +
                    "\n" +
                    "JULIET\tTell me not, friar, that thou hear'st of this,\n" +
                    "\tUnless thou tell me how I may prevent it:\n" +
                    "\tIf, in thy wisdom, thou canst give no help,\n" +
                    "\tDo thou but call my resolution wise,\n" +
                    "\tAnd with this knife I'll help it presently.\n" +
                    "\tGod join'd my heart and Romeo's, thou our hands;\n" +
                    "\tAnd ere this hand, by thee to Romeo seal'd,\n" +
                    "\tShall be the label to another deed,\n" +
                    "\tOr my true heart with treacherous revolt\n" +
                    "\tTurn to another, this shall slay them both:\n" +
                    "\tTherefore, out of thy long-experienced time,\n" +
                    "\tGive me some present counsel, or, behold,\n" +
                    "\t'Twixt my extremes and me this bloody knife\n" +
                    "\tShall play the umpire, arbitrating that\n" +
                    "\tWhich the commission of thy years and art\n" +
                    "\tCould to no issue of true honour bring.\n" +
                    "\tBe not so long to speak; I long to die,\n" +
                    "\tIf what thou speak'st speak not of remedy.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHold, daughter: I do spy a kind of hope,\n" +
                    "\tWhich craves as desperate an execution.\n" +
                    "\tAs that is desperate which we would prevent.\n" +
                    "\tIf, rather than to marry County Paris,\n" +
                    "\tThou hast the strength of will to slay thyself,\n" +
                    "\tThen is it likely thou wilt undertake\n" +
                    "\tA thing like death to chide away this shame,\n" +
                    "\tThat copest with death himself to scape from it:\n" +
                    "\tAnd, if thou darest, I'll give thee remedy.\n" +
                    "\n" +
                    "JULIET\tO, bid me leap, rather than marry Paris,\n" +
                    "\tFrom off the battlements of yonder tower;\n" +
                    "\tOr walk in thievish ways; or bid me lurk\n" +
                    "\tWhere serpents are; chain me with roaring bears;\n" +
                    "\tOr shut me nightly in a charnel-house,\n" +
                    "\tO'er-cover'd quite with dead men's rattling bones,\n" +
                    "\tWith reeky shanks and yellow chapless skulls;\n" +
                    "\tOr bid me go into a new-made grave\n" +
                    "\tAnd hide me with a dead man in his shroud;\n" +
                    "\tThings that, to hear them told, have made me tremble;\n" +
                    "\tAnd I will do it without fear or doubt,\n" +
                    "\tTo live an unstain'd wife to my sweet love.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHold, then; go home, be merry, give consent\n" +
                    "\tTo marry Paris: Wednesday is to-morrow:\n" +
                    "\tTo-morrow night look that thou lie alone;\n" +
                    "\tLet not thy nurse lie with thee in thy chamber:\n" +
                    "\tTake thou this vial, being then in bed,\n" +
                    "\tAnd this distilled liquor drink thou off;\n" +
                    "\tWhen presently through all thy veins shall run\n" +
                    "\tA cold and drowsy humour, for no pulse\n" +
                    "\tShall keep his native progress, but surcease:\n" +
                    "\tNo warmth, no breath, shall testify thou livest;\n" +
                    "\tThe roses in thy lips and cheeks shall fade\n" +
                    "\tTo paly ashes, thy eyes' windows fall,\n" +
                    "\tLike death, when he shuts up the day of life;\n" +
                    "\tEach part, deprived of supple government,\n" +
                    "\tShall, stiff and stark and cold, appear like death:\n" +
                    "\tAnd in this borrow'd likeness of shrunk death\n" +
                    "\tThou shalt continue two and forty hours,\n" +
                    "\tAnd then awake as from a pleasant sleep.\n" +
                    "\tNow, when the bridegroom in the morning comes\n" +
                    "\tTo rouse thee from thy bed, there art thou dead:\n" +
                    "\tThen, as the manner of our country is,\n" +
                    "\tIn thy best robes uncover'd on the bier\n" +
                    "\tThou shalt be borne to that same ancient vault\n" +
                    "\tWhere all the kindred of the Capulets lie.\n" +
                    "\tIn the mean time, against thou shalt awake,\n" +
                    "\tShall Romeo by my letters know our drift,\n" +
                    "\tAnd hither shall he come: and he and I\n" +
                    "\tWill watch thy waking, and that very night\n" +
                    "\tShall Romeo bear thee hence to Mantua.\n" +
                    "\tAnd this shall free thee from this present shame;\n" +
                    "\tIf no inconstant toy, nor womanish fear,\n" +
                    "\tAbate thy valour in the acting it.\n" +
                    "\n" +
                    "JULIET\tGive me, give me! O, tell not me of fear!\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHold; get you gone, be strong and prosperous\n" +
                    "\tIn this resolve: I'll send a friar with speed\n" +
                    "\tTo Mantua, with my letters to thy lord.\n" +
                    "\n" +
                    "JULIET\tLove give me strength! and strength shall help afford.\n" +
                    "\tFarewell, dear father!\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT IV\n" +
                    "\n" +
                    "SCENE II\tHall in Capulet's house.\n" +
                    "\n" +
                    "\t[Enter CAPULET, LADY  CAPULET, Nurse, and two\n" +
                    "\tServingmen]\n" +
                    "\n" +
                    "CAPULET\tSo many guests invite as here are writ.\n" +
                    "\n" +
                    "\t[Exit First Servant]\n" +
                    "\n" +
                    "\tSirrah, go hire me twenty cunning cooks.\n" +
                    "\n" +
                    "Second Servant\tYou shall have none ill, sir; for I'll try if they\n" +
                    "\tcan lick their fingers.\n" +
                    "\n" +
                    "CAPULET\tHow canst thou try them so?\n" +
                    "\n" +
                    "Second Servant\tMarry, sir, 'tis an ill cook that cannot lick his\n" +
                    "\town fingers: therefore he that cannot lick his\n" +
                    "\tfingers goes not with me.\n" +
                    "\n" +
                    "CAPULET\tGo, be gone.\n" +
                    "\n" +
                    "\t[Exit Second Servant]\n" +
                    "\n" +
                    "\tWe shall be much unfurnished for this time.\n" +
                    "\tWhat, is my daughter gone to Friar Laurence?\n" +
                    "\n" +
                    "Nurse\tAy, forsooth.\n" +
                    "\n" +
                    "CAPULET\tWell, he may chance to do some good on her:\n" +
                    "\tA peevish self-will'd harlotry it is.\n" +
                    "\n" +
                    "Nurse\tSee where she comes from shrift with merry look.\n" +
                    "\n" +
                    "\t[Enter JULIET]\n" +
                    "\n" +
                    "CAPULET\tHow now, my headstrong! where have you been gadding?\n" +
                    "\n" +
                    "JULIET\tWhere I have learn'd me to repent the sin\n" +
                    "\tOf disobedient opposition\n" +
                    "\tTo you and your behests, and am enjoin'd\n" +
                    "\tBy holy Laurence to fall prostrate here,\n" +
                    "\tAnd beg your pardon: pardon, I beseech you!\n" +
                    "\tHenceforward I am ever ruled by you.\n" +
                    "\n" +
                    "CAPULET\tSend for the county; go tell him of this:\n" +
                    "\tI'll have this knot knit up to-morrow morning.\n" +
                    "\n" +
                    "JULIET\tI met the youthful lord at Laurence' cell;\n" +
                    "\tAnd gave him what becomed love I might,\n" +
                    "\tNot step o'er the bounds of modesty.\n" +
                    "\n" +
                    "CAPULET\tWhy, I am glad on't; this is well: stand up:\n" +
                    "\tThis is as't should be. Let me see the county;\n" +
                    "\tAy, marry, go, I say, and fetch him hither.\n" +
                    "\tNow, afore God! this reverend holy friar,\n" +
                    "\tOur whole city is much bound to him.\n" +
                    "\n" +
                    "JULIET\tNurse, will you go with me into my closet,\n" +
                    "\tTo help me sort such needful ornaments\n" +
                    "\tAs you think fit to furnish me to-morrow?\n" +
                    "\n" +
                    "LADY CAPULET\tNo, not till Thursday; there is time enough.\n" +
                    "\n" +
                    "CAPULET\tGo, nurse, go with her: we'll to church to-morrow.\n" +
                    "\n" +
                    "\t[Exeunt JULIET and Nurse]\n" +
                    "\n" +
                    "LADY  CAPULET\tWe shall be short in our provision:\n" +
                    "\t'Tis now near night.\n" +
                    "\n" +
                    "CAPULET\tTush, I will stir about,\n" +
                    "\tAnd all things shall be well, I warrant thee, wife:\n" +
                    "\tGo thou to Juliet, help to deck up her;\n" +
                    "\tI'll not to bed to-night; let me alone;\n" +
                    "\tI'll play the housewife for this once. What, ho!\n" +
                    "\tThey are all forth. Well, I will walk myself\n" +
                    "\tTo County Paris, to prepare him up\n" +
                    "\tAgainst to-morrow: my heart is wondrous light,\n" +
                    "\tSince this same wayward girl is so reclaim'd.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT IV\n" +
                    "\n" +
                    "SCENE III\tJuliet's chamber.\n" +
                    "\n" +
                    "\t[Enter JULIET and Nurse]\n" +
                    "\n" +
                    "JULIET\tAy, those attires are best: but, gentle nurse,\n" +
                    "\tI pray thee, leave me to myself to-night,\n" +
                    "\tFor I have need of many orisons\n" +
                    "\tTo move the heavens to smile upon my state,\n" +
                    "\tWhich, well thou know'st, is cross, and full of sin.\n" +
                    "\n" +
                    "\t[Enter LADY CAPULET]\n" +
                    "\n" +
                    "LADY CAPULET\tWhat, are you busy, ho? need you my help?\n" +
                    "\n" +
                    "JULIET\tNo, madam; we have cull'd such necessaries\n" +
                    "\tAs are behoveful for our state to-morrow:\n" +
                    "\tSo please you, let me now be left alone,\n" +
                    "\tAnd let the nurse this night sit up with you;\n" +
                    "\tFor, I am sure, you have your hands full all,\n" +
                    "\tIn this so sudden business.\n" +
                    "\n" +
                    "LADY CAPULET\tGood night:\n" +
                    "\tGet thee to bed, and rest; for thou hast need.\n" +
                    "\n" +
                    "\t[Exeunt LADY CAPULET and Nurse]\n" +
                    "\n" +
                    "JULIET\tFarewell! God knows when we shall meet again.\n" +
                    "\tI have a faint cold fear thrills through my veins,\n" +
                    "\tThat almost freezes up the heat of life:\n" +
                    "\tI'll call them back again to comfort me:\n" +
                    "\tNurse! What should she do here?\n" +
                    "\tMy dismal scene I needs must act alone.\n" +
                    "\tCome, vial.\n" +
                    "\tWhat if this mixture do not work at all?\n" +
                    "\tShall I be married then to-morrow morning?\n" +
                    "\tNo, no: this shall forbid it: lie thou there.\n" +
                    "\n" +
                    "\t[Laying down her dagger]\n" +
                    "\n" +
                    "\tWhat if it be a poison, which the friar\n" +
                    "\tSubtly hath minister'd to have me dead,\n" +
                    "\tLest in this marriage he should be dishonour'd,\n" +
                    "\tBecause he married me before to Romeo?\n" +
                    "\tI fear it is: and yet, methinks, it should not,\n" +
                    "\tFor he hath still been tried a holy man.\n" +
                    "\tHow if, when I am laid into the tomb,\n" +
                    "\tI wake before the time that Romeo\n" +
                    "\tCome to redeem me? there's a fearful point!\n" +
                    "\tShall I not, then, be stifled in the vault,\n" +
                    "\tTo whose foul mouth no healthsome air breathes in,\n" +
                    "\tAnd there die strangled ere my Romeo comes?\n" +
                    "\tOr, if I live, is it not very like,\n" +
                    "\tThe horrible conceit of death and night,\n" +
                    "\tTogether with the terror of the place,--\n" +
                    "\tAs in a vault, an ancient receptacle,\n" +
                    "\tWhere, for these many hundred years, the bones\n" +
                    "\tOf all my buried ancestors are packed:\n" +
                    "\tWhere bloody Tybalt, yet but green in earth,\n" +
                    "\tLies festering in his shroud; where, as they say,\n" +
                    "\tAt some hours in the night spirits resort;--\n" +
                    "\tAlack, alack, is it not like that I,\n" +
                    "\tSo early waking, what with loathsome smells,\n" +
                    "\tAnd shrieks like mandrakes' torn out of the earth,\n" +
                    "\tThat living mortals, hearing them, run mad:--\n" +
                    "\tO, if I wake, shall I not be distraught,\n" +
                    "\tEnvironed with all these hideous fears?\n" +
                    "\tAnd madly play with my forefather's joints?\n" +
                    "\tAnd pluck the mangled Tybalt from his shroud?\n" +
                    "\tAnd, in this rage, with some great kinsman's bone,\n" +
                    "\tAs with a club, dash out my desperate brains?\n" +
                    "\tO, look! methinks I see my cousin's ghost\n" +
                    "\tSeeking out Romeo, that did spit his body\n" +
                    "\tUpon a rapier's point: stay, Tybalt, stay!\n" +
                    "\tRomeo, I come! this do I drink to thee.\n" +
                    "\n" +
                    "\t[She falls upon her bed, within the curtains]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT IV\n" +
                    "\n" +
                    "SCENE IV\tHall in Capulet's house.\n" +
                    "\n" +
                    "\t[Enter LADY CAPULET and Nurse]\n" +
                    "\n" +
                    "LADY CAPULET\tHold, take these keys, and fetch more spices, nurse.\n" +
                    "\n" +
                    "Nurse\tThey call for dates and quinces in the pastry.\n" +
                    "\n" +
                    "\t[Enter CAPULET]\n" +
                    "\n" +
                    "CAPULET\tCome, stir, stir, stir! the second cock hath crow'd,\n" +
                    "\tThe curfew-bell hath rung, 'tis three o'clock:\n" +
                    "\tLook to the baked meats, good Angelica:\n" +
                    "\tSpare not for the cost.\n" +
                    "\n" +
                    "Nurse\tGo, you cot-quean, go,\n" +
                    "\tGet you to bed; faith, You'll be sick to-morrow\n" +
                    "\tFor this night's watching.\n" +
                    "\n" +
                    "CAPULET\tNo, not a whit: what! I have watch'd ere now\n" +
                    "\tAll night for lesser cause, and ne'er been sick.\n" +
                    "\n" +
                    "LADY CAPULET\tAy, you have been a mouse-hunt in your time;\n" +
                    "\tBut I will watch you from such watching now.\n" +
                    "\n" +
                    "\t[Exeunt LADY CAPULET and Nurse]\n" +
                    "\n" +
                    "CAPULET\tA jealous hood, a jealous hood!\n" +
                    "\n" +
                    "\t[Enter three or four Servingmen, with spits, logs,\n" +
                    "\tand baskets]\n" +
                    "\n" +
                    "\t\t          Now, fellow,\n" +
                    "\tWhat's there?\n" +
                    "\n" +
                    "First Servant\tThings for the cook, sir; but I know not what.\n" +
                    "\n" +
                    "CAPULET\tMake haste, make haste.\n" +
                    "\n" +
                    "\t[Exit First Servant]\n" +
                    "\n" +
                    "\t\t  Sirrah, fetch drier logs:\n" +
                    "\tCall Peter, he will show thee where they are.\n" +
                    "\n" +
                    "Second Servant\tI have a head, sir, that will find out logs,\n" +
                    "\tAnd never trouble Peter for the matter.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "CAPULET\tMass, and well said; a merry whoreson, ha!\n" +
                    "\tThou shalt be logger-head. Good faith, 'tis day:\n" +
                    "\tThe county will be here with music straight,\n" +
                    "\tFor so he said he would: I hear him near.\n" +
                    "\n" +
                    "\t[Music within]\n" +
                    "\n" +
                    "\tNurse! Wife! What, ho! What, nurse, I say!\n" +
                    "\n" +
                    "\t[Re-enter Nurse]\n" +
                    "\n" +
                    "\tGo waken Juliet, go and trim her up;\n" +
                    "\tI'll go and chat with Paris: hie, make haste,\n" +
                    "\tMake haste; the bridegroom he is come already:\n" +
                    "\tMake haste, I say.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT IV\n" +
                    "\n" +
                    "SCENE V\tJuliet's chamber.\n" +
                    "\n" +
                    "\t[Enter Nurse]\n" +
                    "\n" +
                    "Nurse\tMistress! what, mistress! Juliet! fast, I warrant her, she:\n" +
                    "\tWhy, lamb! why, lady! fie, you slug-a-bed!\n" +
                    "\tWhy, love, I say! madam! sweet-heart! why, bride!\n" +
                    "\tWhat, not a word? you take your pennyworths now;\n" +
                    "\tSleep for a week; for the next night, I warrant,\n" +
                    "\tThe County Paris hath set up his rest,\n" +
                    "\tThat you shall rest but little. God forgive me,\n" +
                    "\tMarry, and amen, how sound is she asleep!\n" +
                    "\tI must needs wake her. Madam, madam, madam!\n" +
                    "\tAy, let the county take you in your bed;\n" +
                    "\tHe'll fright you up, i' faith. Will it not be?\n" +
                    "\n" +
                    "\t[Undraws the curtains]\n" +
                    "\n" +
                    "\tWhat, dress'd! and in your clothes! and down again!\n" +
                    "\tI must needs wake you; Lady! lady! lady!\n" +
                    "\tAlas, alas! Help, help! my lady's dead!\n" +
                    "\tO, well-a-day, that ever I was born!\n" +
                    "\tSome aqua vitae, ho! My lord! my lady!\n" +
                    "\n" +
                    "\t[Enter LADY CAPULET]\n" +
                    "\n" +
                    "LADY CAPULET\tWhat noise is here?\n" +
                    "\n" +
                    "Nurse\tO lamentable day!\n" +
                    "\n" +
                    "LADY CAPULET\tWhat is the matter?\n" +
                    "\n" +
                    "Nurse\tLook, look! O heavy day!\n" +
                    "\n" +
                    "LADY CAPULET\tO me, O me! My child, my only life,\n" +
                    "\tRevive, look up, or I will die with thee!\n" +
                    "\tHelp, help! Call help.\n" +
                    "\n" +
                    "\t[Enter CAPULET]\n" +
                    "\n" +
                    "CAPULET\tFor shame, bring Juliet forth; her lord is come.\n" +
                    "\n" +
                    "Nurse\tShe's dead, deceased, she's dead; alack the day!\n" +
                    "\n" +
                    "LADY CAPULET\tAlack the day, she's dead, she's dead, she's dead!\n" +
                    "\n" +
                    "CAPULET\tHa! let me see her: out, alas! she's cold:\n" +
                    "\tHer blood is settled, and her joints are stiff;\n" +
                    "\tLife and these lips have long been separated:\n" +
                    "\tDeath lies on her like an untimely frost\n" +
                    "\tUpon the sweetest flower of all the field.\n" +
                    "\n" +
                    "Nurse\tO lamentable day!\n" +
                    "\n" +
                    "LADY CAPULET\t                  O woful time!\n" +
                    "\n" +
                    "CAPULET\tDeath, that hath ta'en her hence to make me wail,\n" +
                    "\tTies up my tongue, and will not let me speak.\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE and PARIS, with Musicians]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tCome, is the bride ready to go to church?\n" +
                    "\n" +
                    "CAPULET\tReady to go, but never to return.\n" +
                    "\tO son! the night before thy wedding-day\n" +
                    "\tHath Death lain with thy wife. There she lies,\n" +
                    "\tFlower as she was, deflowered by him.\n" +
                    "\tDeath is my son-in-law, Death is my heir;\n" +
                    "\tMy daughter he hath wedded: I will die,\n" +
                    "\tAnd leave him all; life, living, all is Death's.\n" +
                    "\n" +
                    "PARIS\tHave I thought long to see this morning's face,\n" +
                    "\tAnd doth it give me such a sight as this?\n" +
                    "\n" +
                    "LADY CAPULET\tAccursed, unhappy, wretched, hateful day!\n" +
                    "\tMost miserable hour that e'er time saw\n" +
                    "\tIn lasting labour of his pilgrimage!\n" +
                    "\tBut one, poor one, one poor and loving child,\n" +
                    "\tBut one thing to rejoice and solace in,\n" +
                    "\tAnd cruel death hath catch'd it from my sight!\n" +
                    "\n" +
                    "Nurse\tO woe! O woful, woful, woful day!\n" +
                    "\tMost lamentable day, most woful day,\n" +
                    "\tThat ever, ever, I did yet behold!\n" +
                    "\tO day! O day! O day! O hateful day!\n" +
                    "\tNever was seen so black a day as this:\n" +
                    "\tO woful day, O woful day!\n" +
                    "\n" +
                    "PARIS\tBeguiled, divorced, wronged, spited, slain!\n" +
                    "\tMost detestable death, by thee beguil'd,\n" +
                    "\tBy cruel cruel thee quite overthrown!\n" +
                    "\tO love! O life! not life, but love in death!\n" +
                    "\n" +
                    "CAPULET\tDespised, distressed, hated, martyr'd, kill'd!\n" +
                    "\tUncomfortable time, why camest thou now\n" +
                    "\tTo murder, murder our solemnity?\n" +
                    "\tO child! O child! my soul, and not my child!\n" +
                    "\tDead art thou! Alack! my child is dead;\n" +
                    "\tAnd with my child my joys are buried.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tPeace, ho, for shame! confusion's cure lives not\n" +
                    "\tIn these confusions. Heaven and yourself\n" +
                    "\tHad part in this fair maid; now heaven hath all,\n" +
                    "\tAnd all the better is it for the maid:\n" +
                    "\tYour part in her you could not keep from death,\n" +
                    "\tBut heaven keeps his part in eternal life.\n" +
                    "\tThe most you sought was her promotion;\n" +
                    "\tFor 'twas your heaven she should be advanced:\n" +
                    "\tAnd weep ye now, seeing she is advanced\n" +
                    "\tAbove the clouds, as high as heaven itself?\n" +
                    "\tO, in this love, you love your child so ill,\n" +
                    "\tThat you run mad, seeing that she is well:\n" +
                    "\tShe's not well married that lives married long;\n" +
                    "\tBut she's best married that dies married young.\n" +
                    "\tDry up your tears, and stick your rosemary\n" +
                    "\tOn this fair corse; and, as the custom is,\n" +
                    "\tIn all her best array bear her to church:\n" +
                    "\tFor though fond nature bids us an lament,\n" +
                    "\tYet nature's tears are reason's merriment.\n" +
                    "\n" +
                    "CAPULET\tAll things that we ordained festival,\n" +
                    "\tTurn from their office to black funeral;\n" +
                    "\tOur instruments to melancholy bells,\n" +
                    "\tOur wedding cheer to a sad burial feast,\n" +
                    "\tOur solemn hymns to sullen dirges change,\n" +
                    "\tOur bridal flowers serve for a buried corse,\n" +
                    "\tAnd all things change them to the contrary.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tSir, go you in; and, madam, go with him;\n" +
                    "\tAnd go, Sir Paris; every one prepare\n" +
                    "\tTo follow this fair corse unto her grave:\n" +
                    "\tThe heavens do lour upon you for some ill;\n" +
                    "\tMove them no more by crossing their high will.\n" +
                    "\n" +
                    "\t[Exeunt CAPULET, LADY CAPULET, PARIS, and FRIAR LAURENCE]\n" +
                    "\n" +
                    "First Musician\tFaith, we may put up our pipes, and be gone.\n" +
                    "\n" +
                    "Nurse\tHonest goodfellows, ah, put up, put up;\n" +
                    "\tFor, well you know, this is a pitiful case.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "First Musician\tAy, by my troth, the case may be amended.\n" +
                    "\n" +
                    "\t[Enter PETER]\n" +
                    "\n" +
                    "PETER\tMusicians, O, musicians, 'Heart's ease, Heart's\n" +
                    "\tease:' O, an you will have me live, play 'Heart's ease.'\n" +
                    "\n" +
                    "First Musician\tWhy 'Heart's ease?'\n" +
                    "\n" +
                    "PETER\tO, musicians, because my heart itself plays 'My\n" +
                    "\theart is full of woe:' O, play me some merry dump,\n" +
                    "\tto comfort me.\n" +
                    "\n" +
                    "First Musician\tNot a dump we; 'tis no time to play now.\n" +
                    "\n" +
                    "PETER\tYou will not, then?\n" +
                    "\n" +
                    "First Musician\tNo.\n" +
                    "\n" +
                    "PETER\tI will then give it you soundly.\n" +
                    "\n" +
                    "First Musician\tWhat will you give us?\n" +
                    "\n" +
                    "PETER\tNo money, on my faith, but the gleek;\n" +
                    "\tI will give you the minstrel.\n" +
                    "\n" +
                    "First Musician\tThen I will give you the serving-creature.\n" +
                    "\n" +
                    "PETER\tThen will I lay the serving-creature's dagger on\n" +
                    "\tyour pate. I will carry no crotchets: I'll re you,\n" +
                    "\tI'll fa you; do you note me?\n" +
                    "\n" +
                    "First Musician\tAn you re us and fa us, you note us.\n" +
                    "\n" +
                    "Second Musician\tPray you, put up your dagger, and put out your wit.\n" +
                    "\n" +
                    "PETER\tThen have at you with my wit! I will dry-beat you\n" +
                    "\twith an iron wit, and put up my iron dagger. Answer\n" +
                    "\tme like men:\n" +
                    "\t'When griping grief the heart doth wound,\n" +
                    "\tAnd doleful dumps the mind oppress,\n" +
                    "\tThen music with her silver sound'--\n" +
                    "\twhy 'silver sound'? why 'music with her silver\n" +
                    "\tsound'? What say you, Simon Catling?\n" +
                    "\n" +
                    "Musician\tMarry, sir, because silver hath a sweet sound.\n" +
                    "\n" +
                    "PETER\tPretty! What say you, Hugh Rebeck?\n" +
                    "\n" +
                    "Second Musician\tI say 'silver sound,' because musicians sound for silver.\n" +
                    "\n" +
                    "PETER\tPretty too! What say you, James Soundpost?\n" +
                    "\n" +
                    "Third Musician\tFaith, I know not what to say.\n" +
                    "\n" +
                    "PETER\tO, I cry you mercy; you are the singer: I will say\n" +
                    "\tfor you. It is 'music with her silver sound,'\n" +
                    "\tbecause musicians have no gold for sounding:\n" +
                    "\t'Then music with her silver sound\n" +
                    "\tWith speedy help doth lend redress.'\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "First Musician\tWhat a pestilent knave is this same!\n" +
                    "\n" +
                    "Second Musician\tHang him, Jack! Come, we'll in here; tarry for the\n" +
                    "\tmourners, and stay dinner.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT V\n" +
                    "\n" +
                    "SCENE I\tMantua. A street.\n" +
                    "\n" +
                    "\t[Enter ROMEO]\n" +
                    "\n" +
                    "ROMEO\tIf I may trust the flattering truth of sleep,\n" +
                    "\tMy dreams presage some joyful news at hand:\n" +
                    "\tMy bosom's lord sits lightly in his throne;\n" +
                    "\tAnd all this day an unaccustom'd spirit\n" +
                    "\tLifts me above the ground with cheerful thoughts.\n" +
                    "\tI dreamt my lady came and found me dead--\n" +
                    "\tStrange dream, that gives a dead man leave\n" +
                    "\tto think!--\n" +
                    "\tAnd breathed such life with kisses in my lips,\n" +
                    "\tThat I revived, and was an emperor.\n" +
                    "\tAh me! how sweet is love itself possess'd,\n" +
                    "\tWhen but love's shadows are so rich in joy!\n" +
                    "\n" +
                    "\t[Enter BALTHASAR, booted]\n" +
                    "\n" +
                    "\tNews from Verona!--How now, Balthasar!\n" +
                    "\tDost thou not bring me letters from the friar?\n" +
                    "\tHow doth my lady? Is my father well?\n" +
                    "\tHow fares my Juliet? that I ask again;\n" +
                    "\tFor nothing can be ill, if she be well.\n" +
                    "\n" +
                    "BALTHASAR\tThen she is well, and nothing can be ill:\n" +
                    "\tHer body sleeps in Capel's monument,\n" +
                    "\tAnd her immortal part with angels lives.\n" +
                    "\tI saw her laid low in her kindred's vault,\n" +
                    "\tAnd presently took post to tell it you:\n" +
                    "\tO, pardon me for bringing these ill news,\n" +
                    "\tSince you did leave it for my office, sir.\n" +
                    "\n" +
                    "ROMEO\tIs it even so? then I defy you, stars!\n" +
                    "\tThou know'st my lodging: get me ink and paper,\n" +
                    "\tAnd hire post-horses; I will hence to-night.\n" +
                    "\n" +
                    "BALTHASAR\tI do beseech you, sir, have patience:\n" +
                    "\tYour looks are pale and wild, and do import\n" +
                    "\tSome misadventure.\n" +
                    "\n" +
                    "ROMEO\t                  Tush, thou art deceived:\n" +
                    "\tLeave me, and do the thing I bid thee do.\n" +
                    "\tHast thou no letters to me from the friar?\n" +
                    "\n" +
                    "BALTHASAR\tNo, my good lord.\n" +
                    "\n" +
                    "ROMEO\t                  No matter: get thee gone,\n" +
                    "\tAnd hire those horses; I'll be with thee straight.\n" +
                    "\n" +
                    "\t[Exit BALTHASAR]\n" +
                    "\n" +
                    "\tWell, Juliet, I will lie with thee to-night.\n" +
                    "\tLet's see for means: O mischief, thou art swift\n" +
                    "\tTo enter in the thoughts of desperate men!\n" +
                    "\tI do remember an apothecary,--\n" +
                    "\tAnd hereabouts he dwells,--which late I noted\n" +
                    "\tIn tatter'd weeds, with overwhelming brows,\n" +
                    "\tCulling of simples; meagre were his looks,\n" +
                    "\tSharp misery had worn him to the bones:\n" +
                    "\tAnd in his needy shop a tortoise hung,\n" +
                    "\tAn alligator stuff'd, and other skins\n" +
                    "\tOf ill-shaped fishes; and about his shelves\n" +
                    "\tA beggarly account of empty boxes,\n" +
                    "\tGreen earthen pots, bladders and musty seeds,\n" +
                    "\tRemnants of packthread and old cakes of roses,\n" +
                    "\tWere thinly scatter'd, to make up a show.\n" +
                    "\tNoting this penury, to myself I said\n" +
                    "\t'An if a man did need a poison now,\n" +
                    "\tWhose sale is present death in Mantua,\n" +
                    "\tHere lives a caitiff wretch would sell it him.'\n" +
                    "\tO, this same thought did but forerun my need;\n" +
                    "\tAnd this same needy man must sell it me.\n" +
                    "\tAs I remember, this should be the house.\n" +
                    "\tBeing holiday, the beggar's shop is shut.\n" +
                    "\tWhat, ho! apothecary!\n" +
                    "\n" +
                    "\t[Enter Apothecary]\n" +
                    "\n" +
                    "Apothecary\tWho calls so loud?\n" +
                    "\n" +
                    "ROMEO\tCome hither, man. I see that thou art poor:\n" +
                    "\tHold, there is forty ducats: let me have\n" +
                    "\tA dram of poison, such soon-speeding gear\n" +
                    "\tAs will disperse itself through all the veins\n" +
                    "\tThat the life-weary taker may fall dead\n" +
                    "\tAnd that the trunk may be discharged of breath\n" +
                    "\tAs violently as hasty powder fired\n" +
                    "\tDoth hurry from the fatal cannon's womb.\n" +
                    "\n" +
                    "Apothecary\tSuch mortal drugs I have; but Mantua's law\n" +
                    "\tIs death to any he that utters them.\n" +
                    "\n" +
                    "ROMEO\tArt thou so bare and full of wretchedness,\n" +
                    "\tAnd fear'st to die? famine is in thy cheeks,\n" +
                    "\tNeed and oppression starveth in thine eyes,\n" +
                    "\tContempt and beggary hangs upon thy back;\n" +
                    "\tThe world is not thy friend nor the world's law;\n" +
                    "\tThe world affords no law to make thee rich;\n" +
                    "\tThen be not poor, but break it, and take this.\n" +
                    "\n" +
                    "Apothecary\tMy poverty, but not my will, consents.\n" +
                    "\n" +
                    "ROMEO\tI pay thy poverty, and not thy will.\n" +
                    "\n" +
                    "Apothecary\tPut this in any liquid thing you will,\n" +
                    "\tAnd drink it off; and, if you had the strength\n" +
                    "\tOf twenty men, it would dispatch you straight.\n" +
                    "\n" +
                    "ROMEO\tThere is thy gold, worse poison to men's souls,\n" +
                    "\tDoing more murders in this loathsome world,\n" +
                    "\tThan these poor compounds that thou mayst not sell.\n" +
                    "\tI sell thee poison; thou hast sold me none.\n" +
                    "\tFarewell: buy food, and get thyself in flesh.\n" +
                    "\tCome, cordial and not poison, go with me\n" +
                    "\tTo Juliet's grave; for there must I use thee.\n" +
                    "\n" +
                    "\t[Exeunt]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT V\n" +
                    "\n" +
                    "SCENE II\tFriar Laurence's cell.\n" +
                    "\n" +
                    "\t[Enter FRIAR JOHN]\n" +
                    "\n" +
                    "FRIAR JOHN\tHoly Franciscan friar! brother, ho!\n" +
                    "\n" +
                    "\t[Enter FRIAR LAURENCE]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tThis same should be the voice of Friar John.\n" +
                    "\tWelcome from Mantua: what says Romeo?\n" +
                    "\tOr, if his mind be writ, give me his letter.\n" +
                    "\n" +
                    "FRIAR JOHN\tGoing to find a bare-foot brother out\n" +
                    "\tOne of our order, to associate me,\n" +
                    "\tHere in this city visiting the sick,\n" +
                    "\tAnd finding him, the searchers of the town,\n" +
                    "\tSuspecting that we both were in a house\n" +
                    "\tWhere the infectious pestilence did reign,\n" +
                    "\tSeal'd up the doors, and would not let us forth;\n" +
                    "\tSo that my speed to Mantua there was stay'd.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tWho bare my letter, then, to Romeo?\n" +
                    "\n" +
                    "FRIAR JOHN\tI could not send it,--here it is again,--\n" +
                    "\tNor get a messenger to bring it thee,\n" +
                    "\tSo fearful were they of infection.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tUnhappy fortune! by my brotherhood,\n" +
                    "\tThe letter was not nice but full of charge\n" +
                    "\tOf dear import, and the neglecting it\n" +
                    "\tMay do much danger. Friar John, go hence;\n" +
                    "\tGet me an iron crow, and bring it straight\n" +
                    "\tUnto my cell.\n" +
                    "\n" +
                    "FRIAR JOHN\tBrother, I'll go and bring it thee.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tNow must I to the monument alone;\n" +
                    "\tWithin three hours will fair Juliet wake:\n" +
                    "\tShe will beshrew me much that Romeo\n" +
                    "\tHath had no notice of these accidents;\n" +
                    "\tBut I will write again to Mantua,\n" +
                    "\tAnd keep her at my cell till Romeo come;\n" +
                    "\tPoor living corse, closed in a dead man's tomb!\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "\tROMEO AND JULIET\n" +
                    "\n" +
                    "ACT V\n" +
                    "\n" +
                    "SCENE III\tA churchyard; in it a tomb belonging to the Capulets.\n" +
                    "\n" +
                    "\t[Enter PARIS, and his Page bearing flowers and a torch]\n" +
                    "\n" +
                    "PARIS\tGive me thy torch, boy: hence, and stand aloof:\n" +
                    "\tYet put it out, for I would not be seen.\n" +
                    "\tUnder yond yew-trees lay thee all along,\n" +
                    "\tHolding thine ear close to the hollow ground;\n" +
                    "\tSo shall no foot upon the churchyard tread,\n" +
                    "\tBeing loose, unfirm, with digging up of graves,\n" +
                    "\tBut thou shalt hear it: whistle then to me,\n" +
                    "\tAs signal that thou hear'st something approach.\n" +
                    "\tGive me those flowers. Do as I bid thee, go.\n" +
                    "\n" +
                    "PAGE\t[Aside]  I am almost afraid to stand alone\n" +
                    "\tHere in the churchyard; yet I will adventure.\n" +
                    "\n" +
                    "\t[Retires]\n" +
                    "\n" +
                    "PARIS\tSweet flower, with flowers thy bridal bed I strew,--\n" +
                    "\tO woe! thy canopy is dust and stones;--\n" +
                    "\tWhich with sweet water nightly I will dew,\n" +
                    "\tOr, wanting that, with tears distill'd by moans:\n" +
                    "\tThe obsequies that I for thee will keep\n" +
                    "\tNightly shall be to strew thy grave and weep.\n" +
                    "\n" +
                    "\t[The Page whistles]\n" +
                    "\n" +
                    "\tThe boy gives warning something doth approach.\n" +
                    "\tWhat cursed foot wanders this way to-night,\n" +
                    "\tTo cross my obsequies and true love's rite?\n" +
                    "\tWhat with a torch! muffle me, night, awhile.\n" +
                    "\n" +
                    "\t[Retires]\n" +
                    "\n" +
                    "\t[Enter ROMEO and BALTHASAR, with a torch,\n" +
                    "\tmattock, &c]\n" +
                    "\n" +
                    "ROMEO\tGive me that mattock and the wrenching iron.\n" +
                    "\tHold, take this letter; early in the morning\n" +
                    "\tSee thou deliver it to my lord and father.\n" +
                    "\tGive me the light: upon thy life, I charge thee,\n" +
                    "\tWhate'er thou hear'st or seest, stand all aloof,\n" +
                    "\tAnd do not interrupt me in my course.\n" +
                    "\tWhy I descend into this bed of death,\n" +
                    "\tIs partly to behold my lady's face;\n" +
                    "\tBut chiefly to take thence from her dead finger\n" +
                    "\tA precious ring, a ring that I must use\n" +
                    "\tIn dear employment: therefore hence, be gone:\n" +
                    "\tBut if thou, jealous, dost return to pry\n" +
                    "\tIn what I further shall intend to do,\n" +
                    "\tBy heaven, I will tear thee joint by joint\n" +
                    "\tAnd strew this hungry churchyard with thy limbs:\n" +
                    "\tThe time and my intents are savage-wild,\n" +
                    "\tMore fierce and more inexorable far\n" +
                    "\tThan empty tigers or the roaring sea.\n" +
                    "\n" +
                    "BALTHASAR\tI will be gone, sir, and not trouble you.\n" +
                    "\n" +
                    "ROMEO\tSo shalt thou show me friendship. Take thou that:\n" +
                    "\tLive, and be prosperous: and farewell, good fellow.\n" +
                    "\n" +
                    "BALTHASAR\t[Aside]  For all this same, I'll hide me hereabout:\n" +
                    "\tHis looks I fear, and his intents I doubt.\n" +
                    "\n" +
                    "\t[Retires]\n" +
                    "\n" +
                    "ROMEO\tThou detestable maw, thou womb of death,\n" +
                    "\tGorged with the dearest morsel of the earth,\n" +
                    "\tThus I enforce thy rotten jaws to open,\n" +
                    "\tAnd, in despite, I'll cram thee with more food!\n" +
                    "\n" +
                    "\t[Opens the tomb]\n" +
                    "\n" +
                    "PARIS\tThis is that banish'd haughty Montague,\n" +
                    "\tThat murder'd my love's cousin, with which grief,\n" +
                    "\tIt is supposed, the fair creature died;\n" +
                    "\tAnd here is come to do some villanous shame\n" +
                    "\tTo the dead bodies: I will apprehend him.\n" +
                    "\n" +
                    "\t[Comes forward]\n" +
                    "\n" +
                    "\tStop thy unhallow'd toil, vile Montague!\n" +
                    "\tCan vengeance be pursued further than death?\n" +
                    "\tCondemned villain, I do apprehend thee:\n" +
                    "\tObey, and go with me; for thou must die.\n" +
                    "\n" +
                    "ROMEO\tI must indeed; and therefore came I hither.\n" +
                    "\tGood gentle youth, tempt not a desperate man;\n" +
                    "\tFly hence, and leave me: think upon these gone;\n" +
                    "\tLet them affright thee. I beseech thee, youth,\n" +
                    "\tPut not another sin upon my head,\n" +
                    "\tBy urging me to fury: O, be gone!\n" +
                    "\tBy heaven, I love thee better than myself;\n" +
                    "\tFor I come hither arm'd against myself:\n" +
                    "\tStay not, be gone; live, and hereafter say,\n" +
                    "\tA madman's mercy bade thee run away.\n" +
                    "\n" +
                    "PARIS\tI do defy thy conjurations,\n" +
                    "\tAnd apprehend thee for a felon here.\n" +
                    "\n" +
                    "ROMEO\tWilt thou provoke me? then have at thee, boy!\n" +
                    "\n" +
                    "\t[They fight]\n" +
                    "\n" +
                    "PAGE\tO Lord, they fight! I will go call the watch.\n" +
                    "\n" +
                    "\t[Exit]\n" +
                    "\n" +
                    "PARIS\tO, I am slain!\n" +
                    "\n" +
                    "\t[Falls]\n" +
                    "\n" +
                    "\tIf thou be merciful,\n" +
                    "\tOpen the tomb, lay me with Juliet.\n" +
                    "\n" +
                    "\t[Dies]\n" +
                    "\n" +
                    "ROMEO\tIn faith, I will. Let me peruse this face.\n" +
                    "\tMercutio's kinsman, noble County Paris!\n" +
                    "\tWhat said my man, when my betossed soul\n" +
                    "\tDid not attend him as we rode? I think\n" +
                    "\tHe told me Paris should have married Juliet:\n" +
                    "\tSaid he not so? or did I dream it so?\n" +
                    "\tOr am I mad, hearing him talk of Juliet,\n" +
                    "\tTo think it was so? O, give me thy hand,\n" +
                    "\tOne writ with me in sour misfortune's book!\n" +
                    "\tI'll bury thee in a triumphant grave;\n" +
                    "\tA grave? O no! a lantern, slaughter'd youth,\n" +
                    "\tFor here lies Juliet, and her beauty makes\n" +
                    "\tThis vault a feasting presence full of light.\n" +
                    "\tDeath, lie thou there, by a dead man interr'd.\n" +
                    "\n" +
                    "\t[Laying PARIS in the tomb]\n" +
                    "\n" +
                    "\tHow oft when men are at the point of death\n" +
                    "\tHave they been merry! which their keepers call\n" +
                    "\tA lightning before death: O, how may I\n" +
                    "\tCall this a lightning? O my love! my wife!\n" +
                    "\tDeath, that hath suck'd the honey of thy breath,\n" +
                    "\tHath had no power yet upon thy beauty:\n" +
                    "\tThou art not conquer'd; beauty's ensign yet\n" +
                    "\tIs crimson in thy lips and in thy cheeks,\n" +
                    "\tAnd death's pale flag is not advanced there.\n" +
                    "\tTybalt, liest thou there in thy bloody sheet?\n" +
                    "\tO, what more favour can I do to thee,\n" +
                    "\tThan with that hand that cut thy youth in twain\n" +
                    "\tTo sunder his that was thine enemy?\n" +
                    "\tForgive me, cousin! Ah, dear Juliet,\n" +
                    "\tWhy art thou yet so fair? shall I believe\n" +
                    "\tThat unsubstantial death is amorous,\n" +
                    "\tAnd that the lean abhorred monster keeps\n" +
                    "\tThee here in dark to be his paramour?\n" +
                    "\tFor fear of that, I still will stay with thee;\n" +
                    "\tAnd never from this palace of dim night\n" +
                    "\tDepart again: here, here will I remain\n" +
                    "\tWith worms that are thy chamber-maids; O, here\n" +
                    "\tWill I set up my everlasting rest,\n" +
                    "\tAnd shake the yoke of inauspicious stars\n" +
                    "\tFrom this world-wearied flesh. Eyes, look your last!\n" +
                    "\tArms, take your last embrace! and, lips, O you\n" +
                    "\tThe doors of breath, seal with a righteous kiss\n" +
                    "\tA dateless bargain to engrossing death!\n" +
                    "\tCome, bitter conduct, come, unsavoury guide!\n" +
                    "\tThou desperate pilot, now at once run on\n" +
                    "\tThe dashing rocks thy sea-sick weary bark!\n" +
                    "\tHere's to my love!\n" +
                    "\n" +
                    "\t[Drinks]\n" +
                    "\n" +
                    "\tO true apothecary!\n" +
                    "\tThy drugs are quick. Thus with a kiss I die.\n" +
                    "\n" +
                    "\t[Dies]\n" +
                    "\n" +
                    "\t[Enter, at the other end of the churchyard, FRIAR\n" +
                    "\tLAURENCE, with a lantern, crow, and spade]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tSaint Francis be my speed! how oft to-night\n" +
                    "\tHave my old feet stumbled at graves! Who's there?\n" +
                    "\n" +
                    "BALTHASAR\tHere's one, a friend, and one that knows you well.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tBliss be upon you! Tell me, good my friend,\n" +
                    "\tWhat torch is yond, that vainly lends his light\n" +
                    "\tTo grubs and eyeless skulls? as I discern,\n" +
                    "\tIt burneth in the Capel's monument.\n" +
                    "\n" +
                    "BALTHASAR\tIt doth so, holy sir; and there's my master,\n" +
                    "\tOne that you love.\n" +
                    "\n" +
                    "FRIAR LAURENCE\t                  Who is it?\n" +
                    "\n" +
                    "BALTHASAR\tRomeo.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tHow long hath he been there?\n" +
                    "\n" +
                    "BALTHASAR\tFull half an hour.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tGo with me to the vault.\n" +
                    "\n" +
                    "BALTHASAR\tI dare not, sir\n" +
                    "\tMy master knows not but I am gone hence;\n" +
                    "\tAnd fearfully did menace me with death,\n" +
                    "\tIf I did stay to look on his intents.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tStay, then; I'll go alone. Fear comes upon me:\n" +
                    "\tO, much I fear some ill unlucky thing.\n" +
                    "\n" +
                    "BALTHASAR\tAs I did sleep under this yew-tree here,\n" +
                    "\tI dreamt my master and another fought,\n" +
                    "\tAnd that my master slew him.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tRomeo!\n" +
                    "\n" +
                    "\t[Advances]\n" +
                    "\n" +
                    "\tAlack, alack, what blood is this, which stains\n" +
                    "\tThe stony entrance of this sepulchre?\n" +
                    "\tWhat mean these masterless and gory swords\n" +
                    "\tTo lie discolour'd by this place of peace?\n" +
                    "\n" +
                    "\t[Enters the tomb]\n" +
                    "\n" +
                    "\tRomeo! O, pale! Who else? what, Paris too?\n" +
                    "\tAnd steep'd in blood? Ah, what an unkind hour\n" +
                    "\tIs guilty of this lamentable chance!\n" +
                    "\tThe lady stirs.\n" +
                    "\n" +
                    "\t[JULIET wakes]\n" +
                    "\n" +
                    "JULIET\tO comfortable friar! where is my lord?\n" +
                    "\tI do remember well where I should be,\n" +
                    "\tAnd there I am. Where is my Romeo?\n" +
                    "\n" +
                    "\t[Noise within]\n" +
                    "\n" +
                    "FRIAR LAURENCE\tI hear some noise. Lady, come from that nest\n" +
                    "\tOf death, contagion, and unnatural sleep:\n" +
                    "\tA greater power than we can contradict\n" +
                    "\tHath thwarted our intents. Come, come away.\n" +
                    "\tThy husband in thy bosom there lies dead;\n" +
                    "\tAnd Paris too. Come, I'll dispose of thee\n" +
                    "\tAmong a sisterhood of holy nuns:\n" +
                    "\tStay not to question, for the watch is coming;\n" +
                    "\tCome, go, good Juliet,\n" +
                    "\n" +
                    "\t[Noise again]\n" +
                    "\n" +
                    "\t\t I dare no longer stay.\n" +
                    "\n" +
                    "JULIET\tGo, get thee hence, for I will not away.\n" +
                    "\n" +
                    "\t[Exit FRIAR LAURENCE]\n" +
                    "\n" +
                    "\tWhat's here? a cup, closed in my true love's hand?\n" +
                    "\tPoison, I see, hath been his timeless end:\n" +
                    "\tO churl! drunk all, and left no friendly drop\n" +
                    "\tTo help me after? I will kiss thy lips;\n" +
                    "\tHaply some poison yet doth hang on them,\n" +
                    "\tTo make die with a restorative.\n" +
                    "\n" +
                    "\t[Kisses him]\n" +
                    "\n" +
                    "\tThy lips are warm.\n" +
                    "\n" +
                    "First Watchman\t[Within]  Lead, boy: which way?\n" +
                    "\n" +
                    "JULIET\tYea, noise? then I'll be brief. O happy dagger!\n" +
                    "\n" +
                    "\t[Snatching ROMEO's dagger]\n" +
                    "\n" +
                    "\tThis is thy sheath;\n" +
                    "\n" +
                    "\t[Stabs herself]\n" +
                    "\n" +
                    "\tthere rust, and let me die.\n" +
                    "\n" +
                    "\t[Falls on ROMEO's body, and dies]\n" +
                    "\n" +
                    "\t[Enter Watch, with the Page of PARIS]\n" +
                    "\n" +
                    "PAGE\tThis is the place; there, where the torch doth burn.\n" +
                    "\n" +
                    "First Watchman\tThe ground is bloody; search about the churchyard:\n" +
                    "\tGo, some of you, whoe'er you find attach.\n" +
                    "\tPitiful sight! here lies the county slain,\n" +
                    "\tAnd Juliet bleeding, warm, and newly dead,\n" +
                    "\tWho here hath lain these two days buried.\n" +
                    "\tGo, tell the prince: run to the Capulets:\n" +
                    "\tRaise up the Montagues: some others search:\n" +
                    "\tWe see the ground whereon these woes do lie;\n" +
                    "\tBut the true ground of all these piteous woes\n" +
                    "\tWe cannot without circumstance descry.\n" +
                    "\n" +
                    "\t[Re-enter some of the Watch, with BALTHASAR]\n" +
                    "\n" +
                    "Second Watchman\tHere's Romeo's man; we found him in the churchyard.\n" +
                    "\n" +
                    "First Watchman\tHold him in safety, till the prince come hither.\n" +
                    "\n" +
                    "\t[Re-enter others of the Watch, with FRIAR LAURENCE]\n" +
                    "\n" +
                    "Third Watchman\tHere is a friar, that trembles, sighs and weeps:\n" +
                    "\tWe took this mattock and this spade from him,\n" +
                    "\tAs he was coming from this churchyard side.\n" +
                    "\n" +
                    "First Watchman\tA great suspicion: stay the friar too.\n" +
                    "\n" +
                    "\t[Enter the PRINCE and Attendants]\n" +
                    "\n" +
                    "PRINCE\tWhat misadventure is so early up,\n" +
                    "\tThat calls our person from our morning's rest?\n" +
                    "\n" +
                    "\t[Enter CAPULET, LADY CAPULET, and others]\n" +
                    "\n" +
                    "CAPULET\tWhat should it be, that they so shriek abroad?\n" +
                    "\n" +
                    "LADY CAPULET\tThe people in the street cry Romeo,\n" +
                    "\tSome Juliet, and some Paris; and all run,\n" +
                    "\tWith open outcry toward our monument.\n" +
                    "\n" +
                    "PRINCE\tWhat fear is this which startles in our ears?\n" +
                    "\n" +
                    "First Watchman\tSovereign, here lies the County Paris slain;\n" +
                    "\tAnd Romeo dead; and Juliet, dead before,\n" +
                    "\tWarm and new kill'd.\n" +
                    "\n" +
                    "PRINCE\tSearch, seek, and know how this foul murder comes.\n" +
                    "\n" +
                    "First Watchman\tHere is a friar, and slaughter'd Romeo's man;\n" +
                    "\tWith instruments upon them, fit to open\n" +
                    "\tThese dead men's tombs.\n" +
                    "\n" +
                    "CAPULET\tO heavens! O wife, look how our daughter bleeds!\n" +
                    "\tThis dagger hath mista'en--for, lo, his house\n" +
                    "\tIs empty on the back of Montague,--\n" +
                    "\tAnd it mis-sheathed in my daughter's bosom!\n" +
                    "\n" +
                    "LADY CAPULET\tO me! this sight of death is as a bell,\n" +
                    "\tThat warns my old age to a sepulchre.\n" +
                    "\n" +
                    "\t[Enter MONTAGUE and others]\n" +
                    "\n" +
                    "PRINCE\tCome, Montague; for thou art early up,\n" +
                    "\tTo see thy son and heir more early down.\n" +
                    "\n" +
                    "MONTAGUE\tAlas, my liege, my wife is dead to-night;\n" +
                    "\tGrief of my son's exile hath stopp'd her breath:\n" +
                    "\tWhat further woe conspires against mine age?\n" +
                    "\n" +
                    "PRINCE\tLook, and thou shalt see.\n" +
                    "\n" +
                    "MONTAGUE\tO thou untaught! what manners is in this?\n" +
                    "\tTo press before thy father to a grave?\n" +
                    "\n" +
                    "PRINCE\tSeal up the mouth of outrage for a while,\n" +
                    "\tTill we can clear these ambiguities,\n" +
                    "\tAnd know their spring, their head, their\n" +
                    "\ttrue descent;\n" +
                    "\tAnd then will I be general of your woes,\n" +
                    "\tAnd lead you even to death: meantime forbear,\n" +
                    "\tAnd let mischance be slave to patience.\n" +
                    "\tBring forth the parties of suspicion.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tI am the greatest, able to do least,\n" +
                    "\tYet most suspected, as the time and place\n" +
                    "\tDoth make against me of this direful murder;\n" +
                    "\tAnd here I stand, both to impeach and purge\n" +
                    "\tMyself condemned and myself excused.\n" +
                    "\n" +
                    "PRINCE\tThen say at once what thou dost know in this.\n" +
                    "\n" +
                    "FRIAR LAURENCE\tI will be brief, for my short date of breath\n" +
                    "\tIs not so long as is a tedious tale.\n" +
                    "\tRomeo, there dead, was husband to that Juliet;\n" +
                    "\tAnd she, there dead, that Romeo's faithful wife:\n" +
                    "\tI married them; and their stol'n marriage-day\n" +
                    "\tWas Tybalt's dooms-day, whose untimely death\n" +
                    "\tBanish'd the new-made bridegroom from the city,\n" +
                    "\tFor whom, and not for Tybalt, Juliet pined.\n" +
                    "\tYou, to remove that siege of grief from her,\n" +
                    "\tBetroth'd and would have married her perforce\n" +
                    "\tTo County Paris: then comes she to me,\n" +
                    "\tAnd, with wild looks, bid me devise some mean\n" +
                    "\tTo rid her from this second marriage,\n" +
                    "\tOr in my cell there would she kill herself.\n" +
                    "\tThen gave I her, so tutor'd by my art,\n" +
                    "\tA sleeping potion; which so took effect\n" +
                    "\tAs I intended, for it wrought on her\n" +
                    "\tThe form of death: meantime I writ to Romeo,\n" +
                    "\tThat he should hither come as this dire night,\n" +
                    "\tTo help to take her from her borrow'd grave,\n" +
                    "\tBeing the time the potion's force should cease.\n" +
                    "\tBut he which bore my letter, Friar John,\n" +
                    "\tWas stay'd by accident, and yesternight\n" +
                    "\tReturn'd my letter back. Then all alone\n" +
                    "\tAt the prefixed hour of her waking,\n" +
                    "\tCame I to take her from her kindred's vault;\n" +
                    "\tMeaning to keep her closely at my cell,\n" +
                    "\tTill I conveniently could send to Romeo:\n" +
                    "\tBut when I came, some minute ere the time\n" +
                    "\tOf her awaking, here untimely lay\n" +
                    "\tThe noble Paris and true Romeo dead.\n" +
                    "\tShe wakes; and I entreated her come forth,\n" +
                    "\tAnd bear this work of heaven with patience:\n" +
                    "\tBut then a noise did scare me from the tomb;\n" +
                    "\tAnd she, too desperate, would not go with me,\n" +
                    "\tBut, as it seems, did violence on herself.\n" +
                    "\tAll this I know; and to the marriage\n" +
                    "\tHer nurse is privy: and, if aught in this\n" +
                    "\tMiscarried by my fault, let my old life\n" +
                    "\tBe sacrificed, some hour before his time,\n" +
                    "\tUnto the rigour of severest law.\n" +
                    "\n" +
                    "PRINCE\tWe still have known thee for a holy man.\n" +
                    "\tWhere's Romeo's man? what can he say in this?\n" +
                    "\n" +
                    "BALTHASAR\tI brought my master news of Juliet's death;\n" +
                    "\tAnd then in post he came from Mantua\n" +
                    "\tTo this same place, to this same monument.\n" +
                    "\tThis letter he early bid me give his father,\n" +
                    "\tAnd threatened me with death, going in the vault,\n" +
                    "\tI departed not and left him there.\n" +
                    "\n" +
                    "PRINCE\tGive me the letter; I will look on it.\n" +
                    "\tWhere is the county's page, that raised the watch?\n" +
                    "\tSirrah, what made your master in this place?\n" +
                    "\n" +
                    "PAGE\tHe came with flowers to strew his lady's grave;\n" +
                    "\tAnd bid me stand aloof, and so I did:\n" +
                    "\tAnon comes one with light to ope the tomb;\n" +
                    "\tAnd by and by my master drew on him;\n" +
                    "\tAnd then I ran away to call the watch.\n" +
                    "\n" +
                    "PRINCE\tThis letter doth make good the friar's words,\n" +
                    "\tTheir course of love, the tidings of her death:\n" +
                    "\tAnd here he writes that he did buy a poison\n" +
                    "\tOf a poor 'pothecary, and therewithal\n" +
                    "\tCame to this vault to die, and lie with Juliet.\n" +
                    "\tWhere be these enemies? Capulet! Montague!\n" +
                    "\tSee, what a scourge is laid upon your hate,\n" +
                    "\tThat heaven finds means to kill your joys with love.\n" +
                    "\tAnd I for winking at your discords too\n" +
                    "\tHave lost a brace of kinsmen: all are punish'd.\n" +
                    "\n" +
                    "CAPULET\tO brother Montague, give me thy hand:\n" +
                    "\tThis is my daughter's jointure, for no more\n" +
                    "\tCan I demand.\n" +
                    "\n" +
                    "MONTAGUE\t                  But I can give thee more:\n" +
                    "\tFor I will raise her statue in pure gold;\n" +
                    "\tThat while Verona by that name is known,\n" +
                    "\tThere shall no figure at such rate be set\n" +
                    "\tAs that of true and faithful Juliet.\n" +
                    "\n" +
                    "CAPULET\tAs rich shall Romeo's by his lady's lie;\n" +
                    "\tPoor sacrifices of our enmity!\n" +
                    "\n" +
                    "PRINCE\tA glooming peace this morning with it brings;\n" +
                    "\tThe sun, for sorrow, will not show his head:\n" +
                    "\tGo hence, to have more talk of these sad things;\n" +
                    "\tSome shall be pardon'd, and some punished:\n" +
                    "\tFor never was a story of more woe\n" +
                    "\tThan this of Juliet and her Romeo.\n" +
                    "\n" +
                    "\t[Exeunt]";
            numOfWords = projectDescription.trim().split("\\s+").length;
            numOfSentence = projectDescription.trim().split("([.!?:;])([\\s\\n])([A-Z]*)").length;
            numOfSyllables = Arrays.stream(projectDescription.trim().split("\\s+")).mapToInt(word -> {

                int syllables = 0;

                ArrayList<Integer> vowels = new ArrayList<>(Arrays. asList(97,101,105,111,117, 65, 69,73, 79, 85));

                if(word.length() <= 3){
                    syllables = 1;
                }else {
                    char[] alphabets = word.toCharArray();

                    char syllableList [] = {'a', 'e', 'i', 'o', 'u', 'y'};

                    // Syllables
                    for (int k = 0; k < syllableList.length; k++)
                    {
                        for (int i = 0; i < projectDescription.length(); i++)
                        {
                            if (projectDescription.charAt(i) == syllableList[k]) {
                                syllables = syllables + 1;
                            }
                            if (lastChar(projectDescription) == 'E' || lastChar(projectDescription) == 'e') {
                                syllables = syllables - 1;
                            } else {
                                break;
                            }
                        }
                    }


                }

               return syllables;
            }).sum();

            fkcl = (206.835 - 84.6 *((numOfSyllables/numOfWords)) - (1.015 *(numOfWords/numOfSentence)));
            fkgl = (0.39 *((numOfSyllables/numOfWords)) + (11.8 *(numOfWords/numOfSentence))  -15.59);

            project.setDescriptionReadability(fkcl);

            if(fkcl > 100){
                educationalLevel = "Early" ;
            }else if(fkcl > 91 && fkcl <= 100){
                educationalLevel = "5th grade" ;
            }else if(fkcl > 81 && fkcl <= 91){
                educationalLevel = "6th grade" ;
            }else if(fkcl > 71 && fkcl <= 81){
                educationalLevel = "7th grade" ;
            }else if(fkcl > 66 && fkcl <= 71){
                educationalLevel = "8th grade" ;
            }else if(fkcl > 61 && fkcl <= 66){
                educationalLevel = "9th grade" ;
            }else if(fkcl > 51 && fkcl <= 61){
                educationalLevel = "High School" ;
            }else if(fkcl > 31 && fkcl <= 51){
                educationalLevel = "Some College" ;
            } else if(fkcl > 0 && fkcl <= 31){
                educationalLevel = "College Graduate" ;
            }else if(fkcl <= 0 ){
                educationalLevel = "Law School Graduate" ;
            }

            project.setEducationalLevel(educationalLevel);

            return fkcl;
        }).average().getAsDouble();

        return searchResultUpadted;
    }
}

