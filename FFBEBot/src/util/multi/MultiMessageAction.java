package util.multi;

import java.io.InputStream;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.requests.RequestFuture;
import net.dv8tion.jda.core.requests.restaction.MessageAction;
import util.Lib;

public class MultiMessageAction extends MessageAction{
	private final MessageAction[] mActions;
	private int tailEnd = 0;
	public MultiMessageAction(JDA api, MessageChannel channel,MessageAction[] mActions){
		super(api, null, channel);
		this.mActions=mActions;
		tailEnd = mActions.length-1;
	}
	@Override
	public MessageAction setCheck(BooleanSupplier checks){
		for(MessageAction action : mActions){
			action.setCheck(checks);
		}
		return this;
	}
	//overrides all other queue methods as they all call this method in superclass
	@Override
	public void queue(Consumer<? super Message> success, Consumer<? super Throwable> failure){
		for(MessageAction action : mActions){
			action.queue(success,failure);
		}
	}
	@Override
	public RequestFuture<Message> submit(boolean shouldQueue){
		for(MessageAction action:mActions){
			action.submit(shouldQueue);
		}
		return null;//not going to use this for now so this will work
	}
	@Override
	public boolean isEmpty(){
		boolean empty = false;
		for(MessageAction action:mActions){
			if(action.isEmpty())
				empty = true;
		}
		return empty;
	}
	@Override
	public boolean isEdit(){
		boolean edit = false;
		for(MessageAction action:mActions){
			if(action.isEdit())
				edit = true;
		}
		return edit;
	}
	@Override
	public MessageAction apply(final Message message){
		//No safe way really to implement same thing in a multi-message context(at least not easily)
		throw new UnsupportedOperationException("Funciton is not implemented");
	}
	@Override
	public MessageAction tts(final boolean isTTS){
		for(int i = 0;i<mActions.length;i++){
			//to satisfy ChekReturnValue annotation
			mActions[i] = mActions[i].tts(isTTS);
		}
		return this;
	}
	public MessageAction reset(){
		for(int i = 0;i<mActions.length;i++){
			//to satisfy CheckReturnValue annotation
			mActions[i] = mActions[i].reset();
		}
		return this;
	}
	//for now do nothing as not certain about what this does
	@Override
	public MessageAction nonce(final String nonce){
		return null;
	}
	@Override
	public MessageAction content(final String content){
		List<String> contents = Lib.splitMessage(content);
		if(contents.size()>mActions.length){
			throw new IllegalArgumentException("Content too long to fit in the multi-messages");
		}
		for(int i = 0; i<mActions.length; i++){
			if(i<contents.size()){
				mActions[i] = mActions[i].content(contents.get(i));
				tailEnd = i;
			}
			else{
				mActions[i] = mActions[i].content(null);//resets other messages if the overall ones exceed
			}
		}
		return this;
	}
	//slightly different action, adds embed to first message without one set
	@Override
	public MessageAction embed(final MessageEmbed embed){
		mActions[mActions.length-1].embed(embed);
		return this;
	}
	@Override
	public MessageAction append(final CharSequence csq, final int start, final int end){
		String[] lines = csq.subSequence(start, end).toString().split("\n");
		int index = -1;
		try{
			for(int i = lines.length;i>=0;i--){
				StringBuilder b = new StringBuilder();
				for(int c = 0;c<i;c++){
					b.append(lines[c]+"\n");
				}
				b.deleteCharAt(b.lastIndexOf("\n"));
				try{
					mActions[tailEnd].append(b);
					index = i;
				}catch(IllegalArgumentException e){
					
				}
			}
		}catch(IllegalArgumentException e){
			CharSequence s = "";
			if(index == -1){
				s = csq.subSequence(start, end).toString();
			}
			else{
				StringBuilder b = new StringBuilder();
				for(int i = 0;i<index;i++){
					b.append(lines[i]+"\n");
				}
				b.deleteCharAt(b.lastIndexOf("\n"));
				s = b;
			}
			tailEnd++;
			return append(s);
		}
		return this;
	}
	@Override
	public MessageAction append(final char c){
		try{
			mActions[tailEnd].append(c);
		}catch(IllegalArgumentException e){
			tailEnd++;
			if(tailEnd==mActions.length){
				throw new IllegalArgumentException("Message content exceeds the length of composing messages");
			}
			mActions[tailEnd].append(c);
		}
		return this;
	}
	@Override
	public MessageAction addFile(final InputStream data, final String name){
		mActions[mActions.length-1].addFile(data,name);
		return this;
	}
	@Override
	public MessageAction clearFiles(){
		for(MessageAction action:mActions){
			action.clearFiles();
		}
		return this;
	}
	@Override
	public MessageAction clearFiles(BiConsumer<String, InputStream> finalizer){
		for(MessageAction action:mActions){
			action.clearFiles(finalizer);
		}
		return this;
	}
	@Override 
	public MessageAction clearFiles(Consumer<InputStream> finalizer){
		for(MessageAction action:mActions){
			action.clearFiles(finalizer);
		}
		return this;
	}
	@Override
	public MessageAction override(final boolean bool){
		for(MessageAction action:mActions){
			action.override(bool);
		}
		return this;
	}
}
