'use strict'

const gulp = require('gulp');
const concat = require('gulp-concat');
const browserSync = require('browser-sync').create();


// Some pointless comments for our project.

var devMode = false;

gulp.task("custom-js", function () {
    gulp.src('./front/js/custom.js')
        .pipe(gulp.dest('dist/js'));
})


gulp.task('css', function() {
    gulp.src("./front/css/main.css")
        .pipe(concat('main.css'))
        .pipe(gulp.dest('dist/css'))
        .pipe(browserSync.reload({
            stream: true
        }));
});


gulp.task('js-lib', function() {
    gulp.src(["./front/js/vendors/angular.js","./front/js/vendors/angular-ui-router.min.js"])
        .pipe(concat('scripts-lib.js'))
        .pipe(gulp.dest('./dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }));
});

gulp.task('js', function() {
    gulp.src("./front/js/app.js")
        .pipe(concat('scripts.js'))
        .pipe(gulp.dest('./dist/js'))
        .pipe(browserSync.reload({
            stream: true
        }));
});
//
gulp.task('html', function() {
    return gulp.src('./front/templates/**/*.html')
        .pipe(gulp.dest('./dist/'))
        .pipe(browserSync.reload({
            stream: true
        }));
});
//
gulp.task('build', function() {
    gulp.start(['js-lib','js','css','html','custom-js'])
});

gulp.task('browser-sync', function() {
    browserSync.init(null, {
        open: false,
        server: {
            baseDir: 'dist',
        }
    });
});

gulp.task('start', function() {
    devMode = true;
    gulp.start(['build', 'browser-sync']);
    gulp.watch(['./front/css/*.css'], ['css']);
    gulp.watch(['./front/js/app.js'], ['js']);
    gulp.watch(['./front/templates/*.html'], ['html']);
});
