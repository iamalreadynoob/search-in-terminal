import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Search on the internet: ");
        linkOpener(editRequest(new Scanner(System.in).nextLine()));
    }

    private static String editRequest(String request)
    {
        String editedReq = null;

        ArrayList<String> words = new ArrayList<>();

        int loc = 0;
        while (loc < request.length())
        {
            if (request.charAt(loc) != ' ')
            {
                String word = null;

                while (loc < request.length() && request.charAt(loc) != ' ')
                {
                    if (word == null) word = Character.toString(request.charAt(loc));
                    else word += Character.toString(request.charAt(loc));
                    loc++;
                }

                words.add(word);
            }

            loc++;
        }

        for (String word: words)
        {
            if (editedReq == null) editedReq = word;
            else editedReq += "+" + word;
        }

        if (editedReq == null) editedReq = "";

        return editedReq;
    }
    private static void linkOpener(String editedReq)
    {
        try
        {
            URI uri = new URI("https://www.google.com/search?channel=fs&client=ubuntu-sn&q=" + editedReq);

            if (Desktop.isDesktopSupported())
            {
                Desktop desktop = Desktop.getDesktop();

                if (desktop.isSupported(Desktop.Action.BROWSE)) desktop.browse(uri);
            }
        }
        catch (URISyntaxException e){e.printStackTrace();} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}