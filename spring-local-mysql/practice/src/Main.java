interface  MediaPlayer{
    void  play(String audioType ,String filename);
}
interface AdvancePlayer{
    void VLCPlay(String fileName);
    void MP4Play(String filename);
}

class VLCPlayer implements AdvancePlayer{
    public void VLCPlay(String filename) {
        System.out.println("playing vlc file" +filename);
    }

    public void MP4Play(String fileName) {
    }
}
class MP4Player implements AdvancePlayer{
    public void VLCPlay(String filename) {
    }

    public void MP4Play(String fileName) {
        System.out.println("playing mp4 file" + fileName);
    }
}

class MediaAdopter implements MediaPlayer{
    AdvancePlayer advancePlayer;

    public  MediaAdopter(String audioType){
        if (audioType.equalsIgnoreCase("vlc")){
            advancePlayer = new VLCPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancePlayer = new MP4Player();
        }
    }

    @Override
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("vlc")){
            advancePlayer.VLCPlay(filename);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancePlayer.MP4Play(filename);
        }
    }
}

class AudioPlayer implements MediaPlayer{
    MediaPlayer mediaPlayer;

    @Override
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("mp3")){
            System.out.println("Playing Mp3 file :"+ filename);
        } else if (audioType.equalsIgnoreCase("mp4")|| audioType.equalsIgnoreCase("vlc")) {
            mediaPlayer = new MediaAdopter(audioType);
            mediaPlayer.play(audioType,filename);

        }
        else {
            System.out.println("Invalid media player file :" +audioType+ "file name"+filename);
        }
    }
}
public class Main {

    public static void main(String[] args) {
        AudioPlayer audioPlayer =new AudioPlayer();
        audioPlayer.play("Mp","Song.pm3");
        audioPlayer.play("vlc","vlcfile.vlc");
        audioPlayer.play("Mp4","Song.mp4");
    }
}