export default function News() {
    return (
        <div className="news">
            <h1>소식</h1>
            <div className="youtube-video">
                <h4>추천 영상</h4>
                <iframe
                    width="100%"
                    height="450"
                    src="https://www.youtube.com/embed/lQ2FIaPGVKY?si=HGZletmriGc2lKef"
                    title="YouTube video player"
                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen>
                </iframe>
            </div>
            <div className="fun-photo">
                <h4>개발 소식</h4>
                <img src="/wow2.png" alt="재미있는 사진" />
            </div>
        </div>
    );
}
