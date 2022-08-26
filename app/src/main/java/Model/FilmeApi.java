package Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmeApi {
    @GET("filmes.jason%2Ffilmes.json?alt=media&token=aa11b971-aaa8-449c-b5e5-12657a8a7f2b")
    Call<List<Filmes>> getFilmes();
}
