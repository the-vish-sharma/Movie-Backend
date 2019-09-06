package in.stackroute.hackathon.moviebackend.favourite.service;

import in.stackroute.hackathon.moviebackend.favourite.dao.FavouriteDaoImp;
import in.stackroute.hackathon.moviebackend.favourite.model.Favourite;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FavouriteService implements FavouriteServiceInterface {

    @Autowired
    private FavouriteDaoImp favouriteDaoImp;

    @Override
    public Map<String, Object> addFavourite(Favourite favourite) {
        favouriteDaoImp.addFavourite(favourite);
        Map<String, Object> map = new TreeMap<>();
        map.put("status", HttpStatus.CREATED);
        map.put("data", favourite);
        map.put("message", "Movie added to favourite list.");
        return map;
    }

    @Override
    public Map<String, Object> removeFavourite(ObjectId _id) {
        favouriteDaoImp.removeFavourite(_id);
        Map<String, Object> map = new TreeMap<>();
        map.put("status", HttpStatus.OK);
        map.put("message", "Movie removed from favourite list.");
        return map;
    }

    @Override
    public Map<String, Object> editComment(ObjectId _id, String comment) {
        Favourite favourite = favouriteDaoImp.editComment(_id, comment);
        Map<String, Object> map = new TreeMap<>();
        map.put("status", HttpStatus.CREATED);
        map.put("data", favourite);
        map.put("message", "Comment updated successfully.");
        return map;
    }

    @Override
    public Map<String, Object> getFavouriteByUsername(String username, int page) {
        List<Favourite> favouriteList = favouriteDaoImp.getFavouriteByUsername(username, page);
        Map<String, Object> map = new TreeMap<>();
        map.put("status", HttpStatus.CREATED);
        map.put("data", favouriteList);
        map.put("count", favouriteList.size());
        map.put("page_no", page);
        map.put("message", (favouriteList.size()==0) ? "No Movie in favourite list." : "All Data loaded.");
        return map;
    }
}
