package solution.Assignment2;
import movieReviewClassification.AbstractReviewHandler;
import solution.Assignment2.MovieReview;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;

public abstract class ReviewHandler extends AbstractReviewHandler {
    public ReviewHandler() {
        super();
    }

    @Override
    public void loadReviews(String filePath, int realClass) {
        // Setup creates the object ad sets our file up for use
        File reviewFile = new File(filePath);
        MovieReview object;

        if (reviewFile.isDirectory())
        {
            /*
            For every string in the list we initialize it to the value returned by readReview
            Then we add it to our data base
             */
            for (String s : reviewFile.list())
            {
                try {
                    object = readReview(s, realClass);
                } catch (IOException e) {
                    System.out.println("Failed to load Reviews (plural)");
                    e.printStackTrace();
                    return;
                }

                database.put( MovieReview.times_constructed , object );
            }

        }
        else
        {
            try {
                object = readReview(reviewFile.getPath(), realClass);
            } catch (IOException e) {
                System.out.println("Failed to load Review (singular)");
                e.printStackTrace();
                return;
            }

            database.put( MovieReview.times_constructed , object );
        }

    }

    @Override
    public MovieReview readReview(String reviewFilePath, int realClass) throws IOException {

        FileReader File = new FileReader(reviewFilePath);
        Scanner fin = new Scanner(File);

        MovieReview toReturn = new MovieReview( fin.nextLine(), realClass ); //// ***** Only gets one line

        return toReturn;
    }

    @Override
    public int classifyReview(MovieReview review) {
        return super.classifyReview(review);
    }

    @Override
    public void deleteReview(int id) {
        if (database.containsKey(id))
        {
            database.remove(id);
        }

        //** does nothing if the key is not there
    }

    @Override
    public void close(Closeable c) {
        super.close(c);
    }

    @Override
    public void saveSerialDB() {
        super.saveSerialDB();
    }

    @Override
    public void loadSerialDB() {
        try{
            FileInputStream fis = new FileInputStream(DATA_FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            database = (HashMap<Integer,MovieReview>)ois.readObject();


            fis.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public MovieReview searchById(int id) {

        /*
        First we use the contains key method of the database to check if the database even has what we are looking for
        then we simply return the movie review whose key coresponds to what is asked
         */
        if (database.containsKey(id))
        {
            return database.get(id);
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<MovieReview> searchBySubstring(String substring) {
        List<MovieReview> toReturn = new ArrayList<>();

        /*
        The idea here is that we turn use the keySet method of the hashmap to give us a list of all the keys that we use
        in the database. We can then iterate through all the keys and discover which ones contains the substring that
        we want
         */
        for (int key : database.keySet())
        {
            if (database.get(key).getText().contains(substring))
            {
                toReturn.add(database.get(key));
            }
        }

        // Return null if empty, return the list otherwise
        if (toReturn.isEmpty())
        {
            return null;
        }
        else
        {
            return toReturn;
        }

    }
}
