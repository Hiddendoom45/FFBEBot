package util.multi;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.requests.Request;
import net.dv8tion.jda.core.requests.RequestFuture;
import net.dv8tion.jda.core.requests.Response;
import net.dv8tion.jda.core.requests.RestAction;

/**
 * Sort of janky way of merging multiple RestActions, 
 * uses extends in order to act masquade as a rest action
 * 
 * Follows the same principles of MultiMessage
 * 
 * <ul>
 * <li>If it is a singleton value that cannot be joined it will return the value
 * of the last action</li>
 * <li>If it is something that can be joined it will join the values from the
 * first to the last action</li>
 * <li>Clear/reset actions will generally affect all actions</li>
 * </ul>
 * 
 * @author Allen
 *
 * @param <T>
 */
public class MultiRestAction<T> extends RestAction<T>{
	private final RestAction<T>[] actions;
	public MultiRestAction(JDA jda, RestAction<T>[] actions){
		super(jda, null);//pass null checks
		this.actions = actions;
	}
	@Override
	public RestAction<T> setCheck(BooleanSupplier checks){
		for(RestAction<T> action : actions){
			action.setCheck(checks);
		}
		return this;
	}
	//overrides all other queue methods as they all call this method in superclass
	@Override
	public void queue(Consumer<? super T> success, Consumer<? super Throwable> failure){
		for(RestAction<T> action : actions){
			action.queue(success,failure);
		}
	}
	@Override
	public RequestFuture<T> submit(boolean shouldQueue){
		for(RestAction<T> action:actions){
			action.submit(shouldQueue);
		}
		return null;//not going to use this for now so this will work
	}
	@Override
	protected void handleResponse(Response response, Request<T> request){
		//null as it's just mimicking a rest action.
	}
	


}
