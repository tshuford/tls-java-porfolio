package tls.test4;
import java.util.*;
public class Solution
{
    // The code calculates the storage used by four types of files (music, images, movies, and other).
    // Even though StringTokenizer is deprecated, I used it instead of String.split() because some of
    // the developer forums seem to think StringTokenizer is much faster than String.split(). Also, using
    // StringTokenizer allowed me to not have to create String arrays for some of the lower level tokens
    // derived from the line tokens that each contained one line of data from the original multi-line input string.
    
    public String solution(String S) {
        String rtnStr = "music 0b\nimages 0b\nmovies 0b\nother 0b\n";
        List<String> lineArry = new ArrayList<>();
        StringTokenizer lineTk = new StringTokenizer(S,"\n");
        long music = 0;
        long image = 0;
        long movie = 0;
        long other = 0;
        
        try {
            
            System.out.println("\nInput file list:\n" + S);
            
            while (lineTk.hasMoreElements())
            {
                lineArry.add(lineTk.nextToken());
            }
            
            StringTokenizer lineItemTk;
            List<String> lineItemArry;
            String extStr = "";
            StringTokenizer extTk;
            String sizeStr;
            StringTokenizer sizeTk;
            
            for (int i= 0; i < lineArry.size(); i++)
            {
                lineItemArry = new ArrayList<>();
                lineItemTk = new StringTokenizer(lineArry.get(i), " ");
                while (lineItemTk.hasMoreElements())
                {
                    lineItemArry.add(lineItemTk.nextToken());
                }
                extStr = "";
                sizeStr = "0";
                
                if (lineItemArry.size() == 2)
                {
                    extTk = new StringTokenizer(lineItemArry.get(0), ".");
                    while (extTk.hasMoreElements())
                    {
                        extStr = extTk.nextToken();
                    }
                    sizeTk = new StringTokenizer(lineItemArry.get(1), "b");
                    if (sizeTk.hasMoreElements())
                    {
                        sizeStr = sizeTk.nextToken();
                    }
                }
                try {
                    if (extStr.equalsIgnoreCase("mp3") || extStr.equalsIgnoreCase("aac")|| extStr.equalsIgnoreCase("flac"))
                    {
                        music += Long.parseLong(sizeStr);
                    }
                    else if (extStr.equalsIgnoreCase("jpg") || extStr.equalsIgnoreCase("bmp")|| extStr.equalsIgnoreCase("gif"))
                    {
                        image += Long.parseLong(sizeStr);
                    }
                    else if (extStr.equalsIgnoreCase("mp4") || extStr.equalsIgnoreCase("avi")|| extStr.equalsIgnoreCase("mkv"))
                    {
                        movie += Long.parseLong(sizeStr);
                    }
                    else
                    {
                        other += Long.parseLong(sizeStr);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                }

            }
            
            rtnStr = "music " + Long.toString(music) + "b\n" +"images " + Long.toString(image) + "b\n" +
                     "movies " + Long.toString(movie) + "b\n" + "other " + Long.toString(other) + "b\n";
            
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        System.out.println("Storage used by each file type:\n" + rtnStr);
        
        return rtnStr;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Solution soltn = new Solution();
        String str1 = "my.song.mp3 11b\ngreatSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b\n";
        String rtn1 = "";
        String str2 = "";
        String rtn2 = "";
        String str3 = "my.song.mp3 11b\ngreatSong.flac 1000b\nmyaac.aac 10b\n"
                + "myimage1.jpg 10b\nmyimage2.bmp 10b\nmyimage3.gif 10b\n"
                + "not3.txt 5b\n"
                + "video.mp4 200b\nvideo.avi 10b\n"
                + "game.exe 100b\nmov!e.mkv 10000b\n";
        String rtn3 = "";
        
        rtn1 = soltn.solution(str1);
        rtn2 = soltn.solution(str2);
        rtn3 = soltn.solution(str3);
        
        return;
    }

}
