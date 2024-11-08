package ahmed.foudi.itlens.utils;

import java.util.List;

public interface ServiceInterface<ID,Request,Response> {
    List<Response> findAll();

    Response findById(ID id);

    Response create(Request dto);

    Response update(ID id, Request dto);

    void delete(ID id);
}
